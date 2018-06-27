var app = getApp()
Page({
  data: {
    logistics: false,
    orderStatus: 1,   // 0未付款 1已接单 2派送中 3已完成 
    business: app.globalData.business,//商家信息
    order: {}
  },
  onShow() {
    var _this = this
  },
  onLoad: function (options) {
    var _this = this
    console.log("订单详情加载时传来的数据", options)
    var orderId = options.orderId
    /**
     * 请求得到订单详情页需要的数据
     */
    wx.request({
      url: app.getHeader() + '/order/getOrderDetailByOrderId',
      data: {
        orderId: orderId
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data, "订单详情页面请求返回的数据")
        if (res.data.status = 200) {
          _this.setData({
            order: res.data.data
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
  calling: function () {
    wx.makePhoneCall({
      phoneNumber: this.data.business.mobile,
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  toApply: function () {
    wx.navigateTo({
      url: '../applyRefund/applyRefund',
    })
  },
  logToTrue: function () {
    this.setData({
      logistics: true
    })
  },
  logToFalse: function () {
    this.setData({
      logistics: false
    })
  },
  toEvaluate: function () {
    wx.navigateTo({
      url: '../evaluate/evaluate',
    })
  },
})