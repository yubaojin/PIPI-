var app = getApp()
Page({
  data: {
    addr: [],
    isClick: false
  },
  onLoad(option) {
    console.log("地址页面=======带过来的值", option);
    if (option.isClick == "true") {
      this.setData({
        isClick: true
      })
    }
  },
  onShow() {
    /**
   * 根据openid请求所有的地址列表
   */
    this.setAddr()
  },
  setAddr() {
    /**
    * 根据openid请求所有的地址列表
    */
    var _this = this
    wx.request({
      url: app.getHeader() + '/address/getAddrsByOpenid',
      data: {
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          console.log(res.data)
          _this.setData({
            addr: res.data.data
          })
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast()
      }
    })
  },
  chooseAddress: function (e) {
    var id = e.currentTarget.dataset.id;
    var _this = this
    wx.request({
      url: app.getHeader() + '/address/changeDefaultAddr',
      data: {
        addrId: id,
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.setAddr()
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast()
      }
    })
  },
  addAddress: function () {
    wx.navigateTo({
      url: '../addAddress/addAddress'
    })
  },
  delAddress: function (e) {
    var _this = this;
    var id = e.currentTarget.dataset.id;
    wx.showModal({
      title: '提示',
      content: '确定删除该地址吗？',
      success: function (res) {
        /**
         * 请求根据用户openid和addId删除地址
         */
        if (res.confirm) {
          wx.request({
            url: app.getHeader() + '/address/delAddrByAddrId',
            data: {
              openid: app.globalData.openid,
              addrId: id
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: function (res) {
              if (res.data.status == 200) {
                _this.setAddr()
              } else {
                app.showToast(res.data.msg)
              }
            },
            fail() {
              app.showToast()
            }
          })
        }
      }
    })
  },
  toEdit: function (e) {
    var id = e.target.dataset.id;
    wx.navigateTo({
      url: '../editAddress/editAddress?id=' + id,
    })
  },
  clickInfo: function (e) {
    var _this = this
    var index = e.currentTarget.dataset.index;
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2]; //上一个页面

    //直接调用上 第 二个页面的setData()方法，把数据存到上一个页面中去
    prevPage.setData({
      selectedAddr: this.data.addr[index],
      backflag:true
    })
    wx.navigateBack({
      delta: 1,
    });
  }
})