//app.js
App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    //获取登录用户唯一id--openId
    var _this = this
    wx.login({
      success: function (res) {
        if (res.code) {
          //发起网络请求
          wx.request({
            url: 'https://api.weixin.qq.com/sns/jscode2session',
            data: {
              js_code: res.code,
              secret: '64e6c766bbdb4878c82002a7ba65f8a5',
              appid: 'wxc3e4ebeda73d029e',
              grant_type: 'authorization_code'
            },
            success: res => {
              _this.globalData.openid = res.data.openid
              console.log("openid=" + _this.globalData.openid)
            }
          })
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    })
    /**
     * 得到商家信息
     */
    wx.request({
      url: _this.getHeader() + '/shop/getInfo',
      data: {
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          _this.globalData.business.name = res.data.data.name
          _this.globalData.business.address = res.data.data.address
          _this.globalData.business.mobile = res.data.data.phone
          _this.globalData.business.time = res.data.data.time
        } else {
          _this.showToast(res.data.msg)
        }
        console.log("请求的商家信息", _this.globalData.business)
      },
      fail() {
        _this.showToast()
      }
    })
  },
  getUserInfo: function (cb) {
    var _this = this
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
      console.log(this.globalData.userInfo, 1)
    } else {
      //调用登录接口
      wx.getUserInfo({
        withCredentials: false,
        success: function (res) {
          _this.globalData.userInfo = res.userInfo
          typeof cb == "function" && cb(_this.globalData.userInfo)
          console.log(_this.globalData.userInfo, 1)
        }
      })
    }
  },
  onLoad: function () {

  },

  globalData: {
    userInfo: null,
    location: "",
    city: '',
    address: '',
    buycar_num: 0,
    totalMoney: 0,
    totalSecond: 899,
    //请求的ip地址和端口号，请求协议
    protocol: "http://",
    // host: "47.106.168.136",
    // port: "8888",
    host: "127.0.0.1",
    port: 8080,
    openid: '',//用户唯一标志ids
    /**
     * 商户信息
     */
    business: {
      name: '',
      address: '',
      time: '',
      mobile: '',
      avatarUrl: '../../img/qishou.png'
    },
  },
  /**
  * 封装请求头
  */
  getHeader() {
    var protocol = this.globalData.protocol
    var host = this.globalData.host
    var port = this.globalData.port
    var header = protocol + host + ':' + port
    return header;
  },
  showToast(title, duration, icon) {
    wx.showToast({
      title: title ? title : '网络繁忙，请扫后重试！',
      duration: duration || 1000,
      icon: icon || 'none'
    })
  }
})
