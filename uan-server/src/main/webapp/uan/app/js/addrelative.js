/**
 *  addrelative.js
 *  功能       添加互助人视图
 *  作者       xxx
 *  日期       2018-08-xx
 *  当前路由 /addrelative
 *  前一路由 /addaccount
 *  后一路由 /checkinfo
 *  输出变量 window.app.addrelativeV
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

    //构造单个互助人账户Form DOM模板
    var relativeFormDomTemplate = '<form class="form-horizontal form-relative" data-bind="attr: {data-index: index}">\
    <a class="fa fa-remove form-remove" data-bind="click: removeAccount"></a>\
    <div class="form-group">\
      <div class="col-xs-3">\
        <label><span style="color:red;">*</span>Full Name</label>\
      </div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="slaveName" placeholder="Full Name"\
          data-bind="value: slaveName, disabled: disabled" autocomplete="off"\
          required validationMessage="Please input full name!">\
      </div>\
    </div>\
    \
    <div class="form-group">\
      <div class="col-xs-3">\
        <label><span style="color:red;">*</span>ID Card No.</label>\
      </div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="slaveId" placeholder="ID Card No."\
          data-bind="value: slaveId, disabled: disabled" autocomplete="off"\
          required pattern="[0-9Xx]{18}" validationMessage="Please input correct ID card number!">\
      </div>\
    </div>\
    \
    <div class="form-group">\
      <div class="col-xs-3">\
      <label><span style="color:red;">*</span>Phone</label>\
      </div>\
      <div class="col-xs-8">\
        <input type="text" class="form-control" name="phone" placeholder="Phone"\
          data-bind="value: phone, disabled: disabled" autocomplete="off"\
          required pattern="[0-9]{11}" validationMessage="Please input correct phone number!">\
      </div>\
    </div>\
    \
    \
    <div class="form-group">\
        <div class="col-xs-3">\
          <label><span style="color:red;">*</span>Relationship</label>\
        </div>\
        <div class="col-xs-8">\
          <input name="relationship" autocomplete="off"\
            data-role="dropdownlist"\
            data-value-primitive="true"\
            data-text-field="name"\
            data-value-field="id"\
            data-bind="value: relationship, source: relations, disabled: disabled"\
            style="width: 100%;"\
            required validationMessage="Please select relationship!"\
          />\
          <span class="k-invalid-msg" data-for="bankOfDeposit"></span>\
    \
      </div>\
    </div>\
    \
    <div class="form-group">\
      <div class="col-xs-3">\
        <label>Face ID</label>\
      </div>\
      <div class="col-xs-8 faceid">\
        <button type="button" class="btn btn-default" name="faceBytes" data-bind="disabled: disabled" >Add Face ID</button>\
      </div>\
    </div>\
    \
    </form>';


  // 构造视图DOM模板
  var addrelativeDomTemplate =  '<header>\
  <div class="prev-btn" data-bind="click: prevPage"><i class="fa fa-chevron-left"></i></div>\
  <div class="header-title">Authorizing Relatives</div>\
</header>\
\
<div class="container-fluid with-bottom" style="padding-top: 15px;"> \
\
  <div class="add-bar add-relative">\
    <button class="btn btn-theme btn-add" data-bind="click: addAccount">Add Relatives</button>\
  </div>\
\
</div>\
\
<div class="btn-bar fix-bottom">\
  <button class="btn btn-default" data-bind="click: prevPage">Previous</button>\
  <button class="btn btn-theme pull-right" data-bind="click: nextPage">Next</button>\
</div>';

  // 使用template构造单个Form DOM字符串
  var relativeFormDomString = kendo.template(relativeFormDomTemplate)({});

  // 使用template构造DOM字符串，传入从上一视图读取的变量
  var addrelativeDomString = kendo.template(addrelativeDomTemplate)({});

  // addrelative视图view modal
  var addrelativeVM = kendo.observable({
    formViews: {},
    formViewsIndex: 0, // 只增不减
    init: function () {
      if(jQuery.fn.niceScroll) {
        $('.container-fluid').last().niceScroll({cursorcolor:'#7f7f7f'});
      }
    },
    // transitionEnd: function() {},
    show: function () {
      if(!window.app.loginV.model.get('name')) {
        console.error('No login information!');
        // route to /login
        app.redirectTo('/login');
        return;
      }

      // 当/addrelative视图不存在form时，添加一个空form
      var addrelativeVM = window.app.addrelativeV.model;
      if(!addrelativeVM.getCurrentViewsNumber()) {
        addrelativeVM.addAccount({
          // "masterId": "110110200001011234",
          // "order": 1,
          // "phone": "13800001111",
          // "relationship": "Son",
          // "slaveId": "220220200001011234",
          // "slaveName": "John Snow"
        }, false);
      }
    },
    // hide: function () {
    //   console.log("view hide", this.customerName);
    // },
    prevPage: function () {
      router.navigate('/addaccount');
    },
    nextPage: function () {
      if(this.validateAccounts(this.get('formViews'))) {
        // TODO - 若所有Form校验成功，则跳转
        router.navigate('/checkinfo');
      } else {
        udesk.messageBox({
          // title: '校验失败',
          // icon: 'fa ',
          message: 'Form validation failed, please check if your input is correct!'
        });
      }
    },
    getCurrentViewsNumber: function() {
      var i, n=0;
      for(i=0; i<this.get('formViewsIndex'); i++) {
        if(this.get('formViews')[i]) {n++;}
      }
      return n;
    },
    getCurrentFirstView: function() {
      var i, n=0;
      for(i=0; i<this.get('formViewsIndex'); i++) {
        if(this.get('formViews')[i]) {return this.get('formViews')[i];}
      }
      return null;
    },
    addAccount: function(card, disabled) {
      var _this = this;
      var defaultMId = window.app.addaccountV.model.getCurrentFirstView().model.idCard;
      
      var newFormVM = kendo.observable({
        index: _this.get('formViewsIndex'), // 此处this为addaccountVM实例
        masterId: card.masterId || defaultMId || '',
        order: card.order || '',
        phone: card.phone || '',
        relationship: card.relationship || '',
        slaveId: card.slaveId || '',
        slaveName: card.slaveName  || '',
        faceBytes: card.faceBytes || 'base64string',
        disabled: disabled || false,
        validator: null,
        removeAccount: function() {
          if(_this.getCurrentViewsNumber() < 1) {
            return;
          }
          
          _this.get('formViews')[this.get('index')].destroy();// 此处this为newFormVM实例
          delete _this.get('formViews')[this.get('index')];
        }, // END OF removeAccount
        // 下拉菜单相关数据
        relationship: card.relationship || '',
        relations: [
          { id: 'Husband', name: 'Husband' },
          { id: 'Wife', name: 'Wife' },
          { id: 'Father', name: 'Father' },
          { id: 'Mother', name: 'Mother' },
          { id: 'Son', name: 'Son' },
          { id: 'Daughter', name: 'Daughter' },
        ],
      }); // END OF newFormVM

      var newFormV = new kendo.View(relativeFormDomString, {
        wrap: false,
        model: newFormVM,
        removeAccount: newFormVM.removeAccount.bind(newFormVM),
      }); // END OF newFormV

      // 动态添加View
      var $newForm = $('.add-relative').before(newFormV.render()).prev();

      newFormV.model.validator = $newForm.kendoValidator({
        // validateOnBlur: false // Disable the default validation on blur
        // validate: function(e) {
        //   console.log(e);
        // }
      }).data('kendoValidator');

      // 保存当前view到formViews
      _this.get('formViews')[_this.get('formViewsIndex')] = newFormV;
      _this.set('formViewsIndex', _this.get('formViewsIndex')+1); // 只增不减


    }, // END OF addAccount
    validateAccount: function(accountView) {
      var model;
      if(accountView && (model = accountView.model)) {
        return model.validator.validate();
      }
      return false;
    }, // END OF validateAccount
    validateAccounts: function() {
      var views = this.get('formViews'), number = this.getCurrentViewsNumber(), i;
      if(!views || !number) {
        return false;
      }
      for(i=0; i<this.get('formViewsIndex'); i++) {
        if(views[i] && !this.validateAccount(views[i])) {
          return false;
        }
      }
      return true;
    }, // END OF validateAccounts

  });

  // addrelative视图view，从字符串变量addrelativeDomString中加载DOM结构，绑定ViewModel
  var addrelativeV = new kendo.View(addrelativeDomString, {
    wrap: false,
    model: addrelativeVM, 
    init: addrelativeVM.init.bind(addrelativeVM),
    show: addrelativeVM.show.bind(addrelativeVM),
    // hide: addrelativeVM.hide.bind(addrelativeVM),
    // transitionEnd: addrelativeVM.transitionEnd.bind(addrelativeVM),
    prevPage: addrelativeVM.prevPage.bind(addrelativeVM),
    nextPage: addrelativeVM.nextPage.bind(addrelativeVM),
    addAccount: addrelativeVM.addAccount.bind(addrelativeVM),
  });

  // addrelative视图路由，渲染指定view
  router.route("/addrelative", function () {
    layout.showIn(layoutSelector, addrelativeV, 'swap');
  });

  
    // 输出至全局变量app
    window.app = $.extend(true, app, {
      'addrelativeV': addrelativeV
    });

}));
