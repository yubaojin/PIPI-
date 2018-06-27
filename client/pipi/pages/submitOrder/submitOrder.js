var app = getApp()
Page({
  data: {
    restaurant: false,
    check: true,
    selectedAddr: {},//选中的地址 初始化为默认地址
    selectedCart: [],//选中的食物
    totalMoney: 0,//总价格
    remarks: '',  //订单备注
    business: app.globalData.business,
    submitflag: true, //点提交后应该让提交按钮失效
    backflag:false
  },
  onLoad() {
    var _this = this
    this.getDefaultAddr();
    this.getSelectedCart();
  }
  ,
  onShow: function (options) {
    if (!this.data.backflag){
      this.getDefaultAddr();
    }
  },
  getDefaultAddr() {
    var _this = this        
    /**
     * 得到默认地址
     */
    wx.request({
      url: app.getHeader() + '/address/getDefaultAddr',
      data: {
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          console.log(res.data, "请求默认地址返回信息")
          _this.setData({
            selectedAddr: res.data.data
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
  getSelectedCart() {
    var _this = this
    /**
     * 得到被选择了的商品
     */
    wx.request({
      url: app.getHeader() + '/cart/getSelectedCart',
      data: {
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)
        if (res.data.status == 200) {
          _this.setData({
            selectedCart: res.data.data
          })
          _this.getTotal()
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
  toMyAddress: function () {
    wx.navigateTo({
      url: '../address/address?isClick=true',
    })
  },
  submitOrder: function () {
    var _this = this
    if (!this.data.selectedAddr.openid) {
      app.showToast("你还没有选着地址！")
      return
    }
    this.setData({
      submitflag: false
    })
    /**
     * 请求生成订单
     */
    wx.request({
      url: app.getHeader() + '/order/saveOrder',
      data: {
        openid: app.globalData.openid,
        totalMoney: _this.data.totalMoney,
        remarks: _this.data.remarks,
        selectedAddrId: _this.data.selectedAddr.id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data, "返回的订单id")
        if (res.data.status == 200) {
          //订单id
          var orderId = res.data.data
          wx.showModal({
            title: '支付',
            content: '立即支付?',
            success: function (res) {
              if (res.confirm) {
                wx.request({
                  url: app.getHeader() + '/order/changeStatus', //仅为示例，并非真实的接口地址
                  data: {
                    orderId: orderId,
                    flag: 2 //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
                  },
                  header: {
                    'content-type': 'application/json' // 默认值
                  },
                  success: function (res) {
                    console.log(res.data, "支付成功************")
                    wx.reLaunch({
                      url: '../index/index?pageType=3'
                    })
                  },
                  fail() {
                    app.showToast()
                    _this.setData({
                      submitflag: true
                    })
                  }
                })

              } else if (res.cancel) {
                console.log("支付取消************")
                wx.reLaunch({
                  url: '../index/index?pageType=3'
                })
              }
            },
            fail() {
              app.showToast()
            }
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
  //购物车的价格数量的计算
  getTotal() {
    var cart = this.data.selectedCart
    var totalMoney = 0
    var buycar_num = 0
    for (var i = 0; i < cart.length; i++) {
      if (cart[i]) {
        totalMoney += Number(cart[i].price * cart[i].num)
        buycar_num += Number(cart[i].num)
      }
    }
    this.setData({
      totalMoney: Number(totalMoney),
      buycar_num: buycar_num
    });
    console.log(totalMoney, buycar_num)
  },
  getRemarkes(e) {
    console.log(e)
    this.setData({
      remarks: e.detail.value
    })
  }
})