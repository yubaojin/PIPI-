var app = getApp();
Page({
  data: {
    addr: '',
    nothing: false,
    isDefault: 0 //是否设为默认 1设为默认 0不是默认
  },
  onLoad() {

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
        url: app.getHeader() + '/address/addAddress',
        data: {
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
          console.log(res.data, "增加地址请求返回")
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