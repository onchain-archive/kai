/**
 *  idcardlogin.js
 *  功能       身份证号登录视图
 *  作者       LIU Jiajie
 *  日期       2018-09-xx
 *  当前路由 /idcardlogin
 *  前一路由 /
 *  后一路由 /uanindex
 *  输出变量  window.app.idcardloginV
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
    var idcardloginDomTemplate = '<a class="atm-btn btn-left btn-bg btn-row-4" data-bind="click: prevPage">退&nbsp;&nbsp;出</a>\
    <a class="atm-btn btn-right btn-bg btn-row-3" data-bind="click: clear">更&nbsp;&nbsp;正</a>\
    <a class="atm-btn btn-right btn-bg btn-row-4" data-bind="click: idcardlogin">确&nbsp;&nbsp;认</a>\
    <div class="container-fluid">\
      <h1 style="margin: 21px auto; width: 340px;">请输入身份证号</h1>\
      <br/><br/><br/>\
      <div class="amt-bar" style="width:600px;">\
        <input type="text" class="form-control" name="amt" autocomplete="off"\
          data-bind="value: idCard">\
      </div>\
    </div>';
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var idcardloginDomString = kendo.template(idcardloginDomTemplate) ({});
  
  
    // idcardlogin视图view modal
    var idcardloginVM = kendo.observable({
      // request
      appId: '',
      idCard: '330330200001011234',
      faceBytes: '',
      userId: '',
      // respond
      id: '',
      name: '',
      phone: '',
      state: '',
      type: '',
      init: function () {
        // console.log('view init', this.customerName);
      },
      show: function () {
        var model = window.app.faceidLoginV.model;
        this.set('appId', model.get('appId'));
        this.set('faceBytes', model.get('faceBytes'));
      },
      // hide: function () {
      //   console.log('view hide', this.customerName);
      // },
      prevPage: function() {
        // 跳转路由至 /
        router.navigate('/');
      },
      clear: function() {
        this.set('idCard', '');
        this.set('userId', '');
      },
      idcardlogin: function() {
        var _this = this, config = window.app.proxy.faceidLogin;
        _this.set('userId', _this.get('idCard'));
        // 使用ajax提交表单
        udesk.ajax({
          type: config.type,
          url: config.url, // TODO- 待修改为后端登录URL
          contentType: config.contentType,
          data: JSON.stringify({
            appId: _this.appId,
            data: {
              idCard: _this.idCard,
              faceBytes: _this.faceBytes,
            },
            userId: _this.userId
          }),
        })
        .done(function(res) {
          // console.log(res);
          // 登录返回成功，将返回的用户数据写入app.idcardloginV.model
          if(res.statusCode == '200') {
            var data = res.data, model = app.idcardloginV.model;
            model.set('id', data.id);
            model.set('idCard', data.idCard);
            model.set('name', data.name);
            model.set('phone', data.phone);
            model.set('state', data.state);
            model.set('type', data.type);
            // 跳转路由至 /uanindex
            router.navigate('/uanindex');
          }
          
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          // TODO - 登录返回失败
  
        }); // END OF ajax
      },
    });
  
    // idcardlogin视图view，从字符串变量idcardloginDomString中加载DOM结构，绑定ViewModel
    var idcardloginV = new kendo.View(idcardloginDomString, {
      model: idcardloginVM,
      // init: idcardloginVM.init.bind(idcardloginVM),
      show: idcardloginVM.show.bind(idcardloginVM),
      // hide: idcardloginVM.hide.bind(idcardloginVM),
      prevPage: idcardloginVM.prevPage.bind(idcardloginVM),
      clear: idcardloginVM.clear.bind(idcardloginVM),
      idcardlogin: idcardloginVM.idcardlogin.bind(idcardloginVM),
    });
  
    // idcardlogin视图路由，渲染指定view
    router.route('/idcardlogin', function () {
      layout.showIn(layoutSelector, idcardloginV);
    });
  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'idcardloginV': idcardloginV
    });
  }));