var app = getApp();
Page({
  data: {
    disk: {},//菜
    evals: {}
  },
  //预览照片
  previewImage(event) {
    var _this = this
    var current = event.currentTarget.dataset.current
    var index = event.currentTarget.dataset.index
    wx.previewImage({
      current: current, // 当前显示图片的http链接
      urls: _this.data.evals.evals[index].imgs // 需要预览的图片http链接列表
    })
  },

  onLoad: function (option) {
    var _this = this
    /**
     * 根据菜的id请求菜的详细信息
     */
    wx.request({
      url: app.getHeader() + '/disk/getDiskById',
      data: {
        diskId: option.id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          console.log("请求菜的详情", res.data)
          _this.setData({
            disk: res.data.data
          })
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast("菜详情请求失败!")
      }
    })
    /**
     * 根据菜的id请求菜的评价
     */
    wx.request({
      url: app.getHeader() + '/evaluate/getEvalsByDiskId',
      data: {
        diskId: option.id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.status == 200) {
          console.log(res.data)
          _this.setData({
            evals: res.data.data
          })
        } else {
          app.showToast(res.data.msg)
        }
      },
      fail() {
        app.showToast("评价请求失败!")
      }
    })

  }
})