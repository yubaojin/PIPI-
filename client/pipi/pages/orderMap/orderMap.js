Page({
  data: {
    hiddenmodalput: true,
    markers: [
      {
        iconPath: "../../img/me.jpg",
        id: 0,
        latitude: 23.099994,
        longitude: 113.324520,
        width: 40,
        height: 40
      },
      {
        iconPath: "../../img/me.jpg",
        id: 1,
        latitude: 23.090094,
        longitude: 113.324520,
        width: 40,
        height: 40
      }
    ],
    polyline: [{
      points: [{
        longitude: 113.324520,
        latitude: 23.099994
      }, {
        longitude: 113.324520,
        latitude: 23.090094
      }],
      color: "#FF0000DD",
      width: 2,
      dottedLine: true
    }],
  },
  //获取当前位置
  onLoad: function () {
    var that = this
    wx.getLocation({
      success: function (res) {
        console.log(res)
        that.setData({
          hasLocation: true,
          location: {
            longitude: res.longitude,
            latitude: res.latitude
          }
        })
      } 
    })
  },
  regionchange(e) {
    console.log(e.type)
  },
  markertap(e) {
    console.log(e.markerId)
  },
  controltap(e) {
    console.log(e.controlId)
  },
  calling: function () {
    wx.makePhoneCall({
      phoneNumber: '18316588252',
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  apply: function() {
    wx.navigateTo({
      url: '../applyRefund/applyRefund',
    })
  }
})