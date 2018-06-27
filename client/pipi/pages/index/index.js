//index.js
var app = getApp()
Page({
  data: {

    scrolltop: 0, //菜页面滑动每次加载时置顶

    pageType: 1,
    // index
    userInfo: {},

    orderOrBusiness: 'order',

    menu: [],//菜单

    height: 0,
    orderId: 1,  //当前菜单的id

    buycar_num: 0,

    // buycar
    totalMoney: 0,
    cart: [],//购物车
    disk: [],//disk 
    // order
    orderOk: false,
    // 用户的头像和名字
    img: '',
    nickName: '',
    //订单
    order: [],
    //商家信息
    business: {},
    //是否开店
    openShop: false,
  },
  onShow() {
    var _this = this
    /**
     * 为何这里要延时
     * 应为openid还在请求中还没赋值
     * 这个函数就调用了 openid就为空 没值
     * 所以延迟1秒
     */
    setTimeout(function () {
      /**
       * 初始化订单
      */
      _this.setOrder()
      /**
       * 初始化购物车列表
       */
      _this.setCart()
    }, 500)

  },
  onLoad: function (option) {
    var _this = this //保留当前app指针
    console.log("提交订单后会跳转过来并带一个参数值", option.pageType)
    if (option.pageType) {
      this.setData({
        pageType: option.pageType
      })
    }
    /**
     * 请求菜单列表
     * */
    _this.setMenu()
    app.getUserInfo(function (userInfo) {
      _this.setData({
        userInfo: userInfo,
        img: userInfo.avatarUrl,
        nickName: userInfo.nickName
      })
    })
    wx.getSystemInfo({
      success: function (res) {
        _this.setData({
          height: (res.windowHeight * .82) + 'px' //菜单高度
        })
      }
    });
    setTimeout(function () {
      //得到商家信息
      _this.setData({
        business: app.globalData.business
      })
    }, 500)
  },
  turnMenu: function (e) {
    var type = e.target.dataset.index;
    console.log(type)
    this.setData({
      orderId: type,
      scrolltop: 0 //每次切换菜单时将菜展示页重新置顶
    })
    this.menuDisk()
  },
  chooseType: function (e) {
    var type = e.currentTarget.dataset.id;
    if (type == 1) {
      wx.setNavigationBarTitle({ title: '外卖' })
      this.setMenu()
      this.setBussiness()
    } else if (type == 2) {
      this.setCart()
      wx.setNavigationBarTitle({ title: '购物车' })
    } else if (type == 3) {
      this.setOrder()
      wx.setNavigationBarTitle({ title: '订单' })
    } else if (type == 4) {
      wx.setNavigationBarTitle({ title: '我的' })
      this.setBussiness()
    }
    this.setData({
      pageType: type
    })
  },

  tabChange: function (e) {
    var type = e.currentTarget.dataset.id;
    this.setData({
      orderOrBusiness: type
    })
    if (type == "business") {
      this.setBussiness()
    }
  },

  toFoodDetail: function (e) {
    var type = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../detail/detail?id=' + type,
    })
  },
  // buycar

  reduce: function (e) {
    var id = e.currentTarget.dataset.id
    var _this = this
    wx.request({
      url: app.getHeader() + '/cart/substractCart',
      data: {
        openid: app.globalData.openid,
        diskId: id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.setCart()
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast()
      }
    })
  },

  add: function (e) {
    var id = e.currentTarget.dataset.id
    var _this = this
    wx.request({
      url: app.getHeader() + '/cart/addCart',
      data: {
        openid: app.globalData.openid,
        diskId: id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.setCart()
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
   * 购物车页面中删除按钮实现
   */
  del: function (e) {
    var _this = this;
    wx.showModal({
      title: '提示',
      content: '确定删除吗',
      success: function (res) {
        if (res.confirm) {
          var id = e.currentTarget.dataset.id;
          wx.request({
            url: app.getHeader() + '/cart/delCartByDiskId',
            data: {
              openid: app.globalData.openid,
              diskId: id
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: function (res) {
              if (res.data.status == 200) {
                _this.setCart()
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
  choose: function (e) {
    var _this = this
    var id = e.currentTarget.dataset.id
    wx.request({
      url: app.getHeader() + '/cart/changeSelected',
      data: {
        diskId: id,
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.setCart()
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
    var cart = this.data.cart
    var totalMoney = 0
    var buycar_num = 0
    for (var i = 0; i < cart.length; i++) {
      if (cart[i].selected) {
        totalMoney += Number(cart[i].price * cart[i].num)
        buycar_num += Number(cart[i].num)
      }
    }
    this.setData({
      totalMoney: Number(totalMoney),
      buycar_num: buycar_num
    });
  },
  toSubmit: function () {
    for (var i = 0; i < this.data.cart.length; i++) {
      if (this.data.cart[i].selected == true) {
        wx.navigateTo({
          url: '../submitOrder/submitOrder',
        })
        return
      }
    }
    app.showToast("至少选着一件商品！")
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
  orderPay(e) {
    var _this = this
    console.log(e.currentTarget.dataset, "去付款按钮带来的数据******")
    var orderId = e.currentTarget.dataset.orderid
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
              _this.setOrder()
            }
          })

        } else if (res.cancel) {
          console.log("支付取消************")
        }
      }
    })
  },
  toMyAddress: function (e) {
    wx.navigateTo({
      url: '../address/address'
    })
  },
  toDetail: function (e) {
    var orderId = e.currentTarget.dataset.orderid
    wx.navigateTo({
      url: '../orderDetail/orderDetail?orderId=' + orderId
    })
  },

  menuDisk() {
    /**
     * 根据菜单id请求菜单详情(不仅要初始化 还要经常调用 所以提出来)
     */
    var _this = this
    wx.request({
      url: app.getHeader() + '/disk/getMenuDisk',
      data: {
        menuId: _this.data.orderId
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.setData({
            disk: res.data.data
          });
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast("菜请求失败!")
      }
    })
  },
  setOrder() {
    var _this = this
    /**
      * 初始化订单
      */
    wx.request({
      url: app.getHeader() + '/order/getAllOrder',
      data: {
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data, "初始化订单***********")
        if (res.data.status == 200) {
          _this.setData({
            order: res.data.data
          })
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast("订单列表请求失败!")
      }
    })
  },
  setCart() {
    var _this = this
    /**
     * 初始化购物车列表
     */
    wx.request({
      url: app.getHeader() + '/cart/getAllCart',
      data: {
        openid: app.globalData.openid
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.setData({
            cart: res.data.data
          })
        } else {
          app.showToast(res.data.msg)
        }
        console.log("购物车初始化：一定要在openid后面", _this.data.cart)
        _this.getTotal() //初始化完购物车马上计算出价格和选中数量
      },
      fail() {
        app.showToast("购物车列表请求失败!")
      }
    })
  },
  setMenu() {
    var _this = this
    /**
     *请求菜单列表（只需要第一次加载时初始化）
    */
    wx.request({
      url: app.getHeader() + '/menu/getAllMenu',
      data: {},
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log("请求菜单列表*******", res.data)
        if (res.data.status == 200) {
          _this.setData({
            menu: res.data.data,
            orderId: res.data.data[1].id
          });
          /**
          *根据菜单id请求菜单详情(初始化 )
          */
          _this.menuDisk()
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast("菜单列表请求失败!")
      }
    })
  },
  cancelOrder(e) {

  },
  receiptOrder(e) {
    var _this = this
    var orderId = e.currentTarget.dataset.orderid
    wx.showModal({
      title: '确认收货',
      content: '请确认是否收到商品?',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: app.getHeader() + '/order/changeStatus', //仅为示例，并非真实的接口地址
            data: {
              orderId: orderId,
              flag: 5 //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: function (res) {
              console.log(res.data, "收货成功************")
              _this.setOrder()
            }
          })

        } else if (res.cancel) {
          console.log("收货取消************")
        }
      }
    })
  },
  setBussiness() {
    if (!this.data.business.name) {
      this.setData({
        business: app.globalData.business
      })
    }
  }
})
