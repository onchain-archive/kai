/**
 *  reportlosscheckinfo.js
 *  功能    挂失-授权人资产详情视图
 *  作者    xxx
 *  日期    2018-09-xx
 *  当前路由 /reportlosscheckinfo
 *  前一路由 /reportlossasset
 *  后一路由 /reportlossresult
 *  输出变量 window.app.reportLossCheckInfoV
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
      var reportLossCheckInfoDomTemplate = '<a class="atm-btn btn-left btn-row-4" data-bind="click: prevPage">取消/Cancle</a>\
      <a class="atm-btn btn-right btn-row-4" data-bind="click: reportLoss">\
        <span class="zh-CN">确认挂失</span>\
        <span class="en-US">Confirm to Report Loss</span>\
      </a>\
      <div class="container-fluid">\
\
      <div class="title">\
        <div class="zh-CN">请核对授权人账户信息</div>\
        <div class="en-US">Pleace check the information of the contracted account.</div>\
      </div>\
\
      <div class="amt-checkinfo-bar form-horizontal">\
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">姓&nbsp;&nbsp;&nbsp;名</div><div class="en-US">Full Name</div>\
            </div>\
          <div class="col-xs-7">\
            <div class="info-value" data-bind="text:customerInformation.name"></div>\
          </div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">账&nbsp;&nbsp;&nbsp;号</div><div class="en-US">Account No.</div>\
            </div>\
          <div class="col-xs-7">\
            <div class="info-value" data-bind="text:code"></div>\
          </div>\
        </div>\
  \
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">电&nbsp;&nbsp;&nbsp;话</div><div class="en-US">Phone</div>\
          </div>\
          <div class="col-xs-7">\
            <div class="info-value" data-bind="text:customerInformation.phone"></div>\
          </div>\
        </div>\
\
        <div class="form-group">\
          <div class="col-xs-5 info-key">\
            <div class="zh-CN">开户行</div><div class="en-US">Bank Of Deposit</div>\
          </div>\
          <div class="col-xs-7">\
            <div class="info-value" data-bind="text:bankOfDeposit"></div>\
          </div>\
        </div>\
\
        <div class="text">\
          <div class="zh-CN">请核对上述信息是否正确，按<b>[确认挂失]</b>键继续</div>\
          <div class="en-US">Please check if the above information is correct,<br> then press <b>[Confirm to Report Loss]</b> to continue.</div>\
        </div>\
      </div>';
  
  
    // 使用template构造DOM字符串，传入从上一视图读取的变量
    var reportLossCheckInfoDomString = kendo.template(reportLossCheckInfoDomTemplate)({});
  
    // reportLossCheckInfo视图view modal
    var reportLossCheckInfoVM = kendo.observable({
      // request
      appId: '',
      amt: '',
      bankOfDeposit: '',
      code: '',
      customerInformation: {
        id: '',
        idCard: '',
        name: '',
        phone: '',
        state: '',
        type: ''
      },
      id: '',
      idCard: '',
      state: '',
      deviceNum: 'ATM模拟器',
      party: '',
      userId: '',
      // respond
      cardNum: '',
      info: '',
      masterName: '',
      // init: function () {
      // },
      show: function () {
        var data = app.reportLossAssetV.model.get('currentRowData'),
            idVM = app.idcardloginV.model;
        // 判断assetV授权人资产表格是否已选中行，否则跳转至 /
        if(!data) {
          // 跳转路由至/
          app.redirectTo();
        }
        this.set('amt', data.amt);
        this.set('bankOfDeposit', data.bankOfDeposit);
        this.set('code', data.code);
        this.set('customerInformation', data.customerInformation);
        this.set('id', data.id);
        this.set('idCard', data.idCard);
        this.set('state', data.state);

        this.set('appId', idVM.get('appId'));
        this.set('party', idVM.get('idCard')); // TODO - 设置Party值为idVM.idCard
        this.set('userId', idVM.get('idCard')); // TODO - 设置userId值为idVM.idCard
        ;
      },
      hide: function () {
        this.set('amt', '');
        this.set('bankOfDeposit', '');
        this.set('code', '');
        this.set('customerInformation', {});
        this.set('id', '');
        this.set('idCard', '');
        this.set('state', '');
        this.set('appId', '');
        // this.set('party', '');
      },
      prevPage: function () {
        router.navigate('/reportlossasset');
      },
      reportLoss: function () {
        var _this = this,
           config = app.proxy.reportLoss;
        // 异步发送挂失请求
        udesk.ajax({
          type: config.type,
          url: config.url,
          contentType: config.contentType,
          data: JSON.stringify({
            appId: _this.get('appId'),
            data: {
              bankOfDeposit: _this.get('bankOfDeposit'),
              code: _this.get('code'),
              deviceNum: _this.get('deviceNum'),
              idCard: _this.get('customerInformation').idCard, // TODO - 发送customerInformation.idCard
              party: _this.get('party'),
              state: 'ABNORMAL' // 设置为 'ABNORMAL'
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
          // 挂失成功，将返回的用户数据写入app.reportLossCheckInfoV.model
          var data = res.data, ciVM = app.reportLossCheckInfoV.model;
          ciVM.set('cardNum', data.cardNum);
          ciVM.set('info', data.info);
          ciVM.set('masterName', data.masterName);
          // 跳转路由至 /reportlossresult
          router.navigate('/reportlossresult');
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
          // TODO - 提交返回失败
        });


      },
    });
  
    // reportLossCheckInfo视图view，从字符串变量reportLossCheckInfoDomString中加载DOM结构，绑定ViewModel
    var reportLossCheckInfoV = new kendo.View(reportLossCheckInfoDomString, {
      model: reportLossCheckInfoVM, 
      // init: reportLossCheckInfoVM.init.bind(reportLossCheckInfoVM),
      show: reportLossCheckInfoVM.show.bind(reportLossCheckInfoVM),
      hide: reportLossCheckInfoVM.hide.bind(reportLossCheckInfoVM),
      prevPage: reportLossCheckInfoVM.prevPage.bind(reportLossCheckInfoVM),
      reportLoss: reportLossCheckInfoVM.reportLoss.bind(reportLossCheckInfoVM)
    });
  
    // reportLossCheckInfo视图路由，渲染指定view
    router.route('/reportlosscheckinfo', function () {
      layout.showIn(layoutSelector,reportLossCheckInfoV);
    });
  
      // 输出至全局变量app
      window.app = $.extend(true, app, {
        'reportLossCheckInfoV': reportLossCheckInfoV
      });
  
  }));
  