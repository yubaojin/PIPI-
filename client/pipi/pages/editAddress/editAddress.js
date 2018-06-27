var app = getApp()
Page({
  data: {
    //页面数据
    id: 1,
    name: '',
    gender: 1,
    mobile: '',
    addr: '',
    detail: '',
    isDefault: 1,
    //购物车时候有东西
    nothing: false,
    isDefault: 0 //是否设为默认 1设为默认 0不是默认
  },
  onLoad(option) {
    var _this = this
    /**
     *  得到地址信息
     */
    wx.request({
      url: app.getHeader() + '/address/getAddrByAddrId',
      data: {
        addrId: option.id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data, "editAddress界面获取地址根据id")
        if (res.data.status == 200) {
          _this.setData({
            id: res.data.data.id,
            gender: res.data.data.gender,
            mobile: res.data.data.mobile,
            name: res.data.data.name,
            isDefault: res.data.data.isDefault,
            addr: res.data.data.address.split(" ")[0],
            detail: res.data.data.address.split(" ")[1]
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
  /**
   * 调用函数获取到地址地区
   */
  getAddress: function () {
    var _this = this;
    wx.chooseLocation({
      success: function (res) {
        if (res.address.length > 10) {
          console.log(res)
          res.address = res.address
        }
        _this.setData({
          addr: res.address
        })
      },
    })
  },
  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
    var a = e.detail.value
    var _this = this
    if (this.validate(a)) {
      wx.showToast({
        title: _this.validate(a),
        icon: 'none',
        duration: 500
      })
    } else {
      wx.request({
        url: app.getHeader() + '/address/updateAddress',
        data: {
          id: _this.data.id,
          openid: app.globalData.openid.trim(),
          name: a.name.trim(),
          mobile: a.mobile.trim(),
          gender: a.gender,
          address: (a.addr + " " + a.detail).trim(),
          isDefault: _this.data.isDefault
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          console.log(res.data, "++++++++++++++++++")
          if (res.data.status == 200) {
            console.log(res)
            wx.navigateBack({
              url: '../address/address'
            })
          } else {
            app.showToast(res.data.msg)
          }
        },
        fail() {
          app.showToast()
        }
      })
    }
  },
  /**
   * 设为默认地址修改
   */
  changeDefault() {
    this.setData({
      isDefault: (this.data.isDefault + 1) % 2
    })
  },

  validate(a) {
    if (!a.name) {
      return "名字不能为空!";
    }
    if (!a.mobile) {
      return "手机不能为空!";
    }
    if (!/^1\d{10}$/.test(a.mobile)) {
      return "号码格式错误！"
    }
    if (!a.addr) {
      return "地区不能为空!";
    }
    if (!a.detail) {
      return "详细不能为空!";
    }
    return false;
  }
})