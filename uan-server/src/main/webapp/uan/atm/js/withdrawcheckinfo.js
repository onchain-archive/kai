/**
 *  withdrawcheckinfo.js
 *  功能    取款-确认金额视图
 *  作者    LIU Jiajie
 *  日期    2018-09-xx
 *  当前路由 /withdrawcheckinfo
 *  前一路由 /
 *  后一路由 /withdrawresult
 *  输出变量  window.app.withdrawCheckInfoV
 * 
 * Copyright Agriculture Bank of China.
 */

;(function(factory) {
    if (typeof define === 'function' && define.amd) {
      // AMD. Register as anonymous module.
      define(['jquery'], function(jQuery) {
        return factory(jQuery, window, document);
      });
    } else if (typeof module === 'object' && module.exports) {
      // Node. Does not work with strict CommonJS, but
      // only CommonJS-like enviroments that support module.exports,
      // like Node.
      module.exports = factory(require('jquery'), window, document);
    } else {
      // Browser globals.
      factory(jQuery, window, document);
    }
  }(function($, window, document, undefined) {
    'use strict';

  // 获取全局变量，若为空则对其初始化
  var kendo = window.kendo,
    app = window.app,
    layout = app.layout,
    router = app.router,
    layoutSelector = app.layoutSelector;
  
    // 构造视图DOM模板
    var withdrawCheckInfoDomTemplate = '<a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">返回修改</a>\
    <a class="atm-btn btn-right btn-confirm btn-row-4" data-bind="click: withdraw">确认取款</a>\
    <div class="container-fluid">\
      <h1 style="margin: 21px auto; width: 340px;">请核对取款信息</h1>\
      <br/><br/><br/>\
      <div class="amt-checkinfo-bar form-horizontal">\
\
        <div class="form-group">\
          <div class="col-xs-4"><label>姓&nbsp;&nbsp;&nbsp;&nbsp;名</label></div>\
          <div class="col-xs-8"><div class="info-value" data-bind="text:name"></div></div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-4"><label>身份证号</label></div>\
          <div class="col-xs-8"><div class="info-value" data-bind="text:userId"></div></div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-4"><label>取款金额</label></div>\
          <div class="col-xs-8">\
            <div class="info-value">\
            ￥<span data-bind="text:amt"></span>.00\
            </div>\
          </div>\
        </div>\
      </div>\
    </div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var withdrawCheckInfoDomString = kendo.template(withdrawCheckInfoDomTemplate)({});
  
  
    // withdrawCheckInfo视图view modal
    var withdrawCheckInfoVM = kendo.observable({
      // request
      appId: '',
      amt: '',
      deviceNum: 'ATM模拟器',
      name: '',
      userId: '',
      party: '',
      // respond
      cardNum: '',
      info: '',
      masterName: '',
      // init: function () {
      // },
      show: function () {
        var idVM     = app.idcardloginV.model,
          withdrawVM = app.withdrawV.model;
        // 判断是否已登录
        if(!idVM.get('name')) {
          // 跳转路由至 /
          app.redirectTo();
        } else if(!withdrawVM.get('amt')) {
          // 跳转路由至 /withdraw
          app.redirectTo('/withdraw');
        }
        this.set('appId', idVM.get('appId'));
        this.set('amt', withdrawVM.get('amt'));
        this.set('name', idVM.get('name'));
        this.set('userId', idVM.get('userId'));
        this.set('party', idVM.get('userId')); // TODO - 设置party为userId
      },
      hide: function () {
        this.set('appId', '');
        this.set('amt', '');
        this.set('name', '');
        this.set('userId', '');
        this.set('party', ''); // TODO - 设置party为userId
      },
      prevPage: function() {
        // 跳转路由至 //withdraw
        router.navigate('/withdraw');
      },
      // cancle: function() {
      // },
      withdraw: function() {
        var _this = this, config = app.proxy.withdraw;
        udesk.ajax({
          type: config.type,
          url: config.url, // TODO- 待修改为后端登录URL
          contentType: config.contentType,
          data: JSON.stringify({
            appId: _this.get('appId'),
            data: {
              amt: _this.get('amt'),
              deviceNum: _this.get('deviceNum'),
              party: _this.get('party')
            },
            userId: _this.get('userId')
          })
        })
        .done(function(res) {
          if(res.statusCode != '200') { 
            // TODO - 设置promise为false
            console.error(res.message);
            return;
          }
          // 取款成功，将返回的用户数据写入app.withdrawCheckInfoV.model
          var data = res.data, ciVM = app.withdrawCheckInfoV.model;
          ciVM.set('cardNum', data.cardNum);
          ciVM.set('info', data.info);
          ciVM.set('masterName', data.masterName);
          // 跳转路由至 /withdrawResult
          router.navigate('/withdrawresult');
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          // TODO - 提交返回失败
        });
      },

    });
  
    // withdrawCheckInfo视图view，从字符串变量withdrawCheckInfoDomString中加载DOM结构，绑定ViewModel
    var withdrawCheckInfoV = new kendo.View(withdrawCheckInfoDomString, {
      model: withdrawCheckInfoVM,
      // init: withdrawCheckInfoVM.init.bind(withdrawCheckInfoVM),
      show: withdrawCheckInfoVM.show.bind(withdrawCheckInfoVM),
      hide: withdrawCheckInfoVM.hide.bind(withdrawCheckInfoVM),
      withdraw: withdrawCheckInfoVM.withdraw.bind(withdrawCheckInfoVM),
      // cancle: withdrawCheckInfoVM.cancle.bind(withdrawCheckInfoVM),
      prevPage: withdrawCheckInfoVM.prevPage.bind(withdrawCheckInfoVM),
    });
  
    // withdrawCheckInfo视图路由，渲染指定view
    router.route('/withdrawcheckinfo', function () {
      layout.showIn(layoutSelector, withdrawCheckInfoV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'withdrawCheckInfoV': withdrawCheckInfoV
    });
  }));