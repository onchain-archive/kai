/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 38);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports) {

module.exports = jQuery;

/***/ }),

/***/ 38:
/***/ (function(module, exports, __webpack_require__) {

var __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;/**
 * udesk.messages.en-US.js
 * Version 3.4.0
 * Date 2018-03-14
 * Author LIU Jiajie
 * //dev.udesk.abc/cdn/framework/3.4.0/messages/udesk.messages.en-US.js
 *
 * Usage:
 *   include this file after udesk.js or udesk.portal.js
 *
 * Copyright Agriculture Bank of China.
 */

// API和组件国际化支持模块：English-US
;(function(root, factory) {
  if (true) {
    // AMD. Register as anonymous module.
    !(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(0)], __WEBPACK_AMD_DEFINE_RESULT__ = function(jQuery) {
      return (window.udesk = factory(jQuery, window, document));
    }.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__),
				__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));
  } else if (typeof module === 'object' && module.exports) {
    // Node. Does not work with strict CommonJS, but
    // only CommonJS-like enviroments that support module.exports,
    // like Node.
    module.exports = factory(require('jquery'), window, document);
  } else {
    // Browser globals.
    window.udesk = factory(jQuery, window, document);
  }
}(this, function($, window, document, undefined) {
  'use strict';

  var udesk = window.udesk;

  if (!$ || !jQuery || !udesk) return {};
  var fn = jQuery.fn;

  // 设置en-US模板
  udesk.setDefaults({
    notice: {
      // notice templates
      templates: [{
        type: 'success',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><h4><b>Success</b></h4>'
          + '#if(message){#<p class="message-brief">#=message#</p>#}#'
          + '#if(detail && detail.length > 33){#<p class="message-detail"><a data-type="success">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden-detail">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p> #}#'
          + '</div><a class="notice-close fa fa-remove"></a>'
          +'</div>'
      }, {
        type: 'warning',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><h4><b>Warning</b></h4>'
          + '#if(message){#<p class="message-brief">#=message#</p>#}#'
          + '#if(detail && detail.length > 33){#<p class="message-detail"><a data-type="warning">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden-detail">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p> #}#'
          + '</div><a class="notice-close fa fa-remove"></a></div>'
      }, {
        type: 'error',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><h4><b>Error</b></h4>'
          + '#if(message){#<p class="message-brief">#=message#</p>#}#'
          + '#if(detail && detail.length > 33){#<p class="message-detail"><a data-type="error">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden-detail">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p> #}#'
          + '</div><a class="notice-close fa fa-remove"></a></div>'
      }, {
        type: 'info',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><div><h4><b>Info</b></h4>'
          + '#if(message){#<p class="message-brief">#=message#</p>#}#'
          + '#if(detail && detail.length > 33){#<p class="message-detail"><a data-type="info">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden-detail">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p> #}#'
          + '</div><a class="notice-close fa fa-remove"></a></div>'
      }], // end of template
    },
    // 框架内置modal组件的默认配置项
    modal: {
      confirmChoice: [{
        display: 'Yes',
        code: 'YES'
      },{
        display: 'No',
        code: 'NO'
      // },{
      //   display: 'Cancel',
      //   code: 'CANCEL'
      }],
      // 通知框模板
      templates: [{
        type: 'confirm',
        template: '{{var data = it;}}<div class="modal fade confirm-modal" tabindex="-1" role="dialog" aria-labelledby="udeskModalLabel" aria-hidden="true">'
          + '<div class="modal-dialog">'
          + '<div class="modal-content">'
          + '<div class="modal-header">'
          + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
          + '<h4 class="modal-title"><i class="{{if(data.icon){ }} {{=data.icon}} {{ }else{ }}fa fa-exclamation{{ } }}"></i>'
          + '<b>{{if(data.title){ }} {{=data.title}} {{ }else{ }} Operation Confirm {{ } }}</b></h4>'
          + '</div>'
          + '<div class="modal-body">'
          + '{{if(data.message){ }}<div class="message">{{=data.message}}</div>{{ }else{ }} Sure to perform the operation? {{ } }}'
          + '{{if(data.detail) { }}<div class="detail"> {{=data.detail }}</div>{{ } }}'
          + '</div>'
          + '<div class="modal-footer">'
          + '{{var choice = data.choice; for(var i=0; i<choice.length; i++){ }}'
          + '{{if( data.defaultChoice == i ){ }}'
          + '<button type="button" class="btn active btn-theme" data-dismiss="modal" data-code="{{=choice[i].code}}" id="choiceBtn{{=choice[i].code}}">{{=choice[i].display}}</button>'
          + '{{ }else{ }}'
          + '<button type="button" class="btn" data-dismiss="modal" data-code="{{=choice[i].code}}" id="choiceBtn{{=choice[i].code}}">{{=choice[i].display}}</button>'
          + '{{ } }}'
          + '{{ } }}'
          + '</div></div></div></div>'
      },{
          type: 'messageDetail',
          template: '{{var data = it;}} <div class="modal fade error-modal" tabindex="-1" role="dialog" aria-hidden="true">'
          + '<div class="modal-dialog">'
          + '<div class="modal-content">'
          + '<div class="modal-header">'
          + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
          + '<h4 class="modal-title"><i class="{{if(data.icon){ }} {{=data.icon}} {{ }else{ }}fa fa-envelope-o{{ } }}"></i>'
          + '<b>{{if(data.title){ }} {{=data.title}} {{ }else{ }} Notice Detail {{ } }}</b></h4>'
          + '</div>'
          + '<div class="modal-body">'
          + '{{if(data.message){ }}<div class="message">{{=data.message}}</div>{{ } }}'
          + '{{if(data.detail) { }}<div class="detail"> {{=data.detail }}</div>{{ } }}'
          + '</div>'
          + '<div class="modal-footer">'
          + '<button type="button" class="btn btn-theme" data-dismiss="modal">Close</button>'
          + '</div></div></div></div>'
      },{
        type: 'messageBox',
        template: '{{var data = it;}}<div class="modal fade error-modal" tabindex="-1" role="dialog" aria-labelledby="u-modal-label" aria-hidden="true">'
          + '<div class="modal-dialog">'
          + '<div class="modal-content">'
          + '<div class="modal-header">'
          + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
          + '<h4 class="modal-title"><i class="{{if(data.icon){ }} {{=data.icon}} {{ }else{ }}fa fa-envelope-o{{ } }}"></i>'
          + '<b>{{if(data.title){ }} {{=data.title}} {{ }else{ }} Message {{ } }}</b></h4>'
          + '</div>'
          + '<div class="modal-body">'
          + '{{if(data.message){ }}<div class="message">{{=data.message}}</div>{{ } }}'
          + '</div>'
          + '<div class="modal-footer">'
          + '<button type="button" class="btn" data-dismiss="modal">Close</button>'
          + '</div></div></div></div>'
      }], // end of templates
    }, // end of modal
    // ajax默认配置
    ajax: {
      defaultNotice: {
        success: true,
        message: 'Operation done!',
        detail: null
      }
    }, // end of ajax
    // 右下角返回顶部按钮 默认配置项
    scrollBack: {
      template: '<div id="s_top_feed" class="back-top"><div class="to-top" data-tool="1">' 
                + '<div class="icon-mask"><div class="text">TO TOP</div></div><span class="fa fa-angle-up">'
                + '</span></div></div>'
    }
  }); // end of udesk.setDefaults

  // 修改udeskMenuSearch组件默认值
  if(fn.udeskMenuSearch) {
    jQuery.extend(true, fn.udeskMenuSearch.defaults, {
      placeholder: 'Search menu by title'
    });
  }

  // 修改udeskModal组件默认值
  if(fn.udeskModal) {
    jQuery.extend(true, fn.udeskModal.defaults, {
      confirmChoice: [{
        display: 'Yes',
        code: 'YES'
      },{
        display: 'No',
        code: 'NO'
      // },{
      //   display: 'Cancel',
      //   code: 'CANCEL'
      }],
      // 通知框模板
      templates: [{
        type: 'confirm',
        template: '{{var data = it;}}<div class="modal fade confirm-modal" tabindex="-1" role="dialog" aria-labelledby="udeskModalLabel" aria-hidden="true">'
          + '<div class="modal-dialog">'
          + '<div class="modal-content">'
          + '<div class="modal-header">'
          + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
          + '<h4 class="modal-title"><i class="{{if(data.icon){ }} {{=data.icon}} {{ }else{ }}fa fa-exclamation{{ } }}"></i>'
          + '<b>{{if(data.title){ }} {{=data.title}} {{ }else{ }} Operation Confirom {{ } }}</b></h4>'
          + '</div>'
          + '<div class="modal-body">'
          + '{{if(data.message){ }}<div class="message">{{=data.message}}</div>{{ }else{ }} Sure to perform the operation? {{ } }}'
          + '{{if(data.detail) { }}<div class="detail"> {{=data.detail }}</div>{{ } }}'
          + '</div>'
          + '<div class="modal-footer">'
          + '{{var choice = data.choice; for(var i=0; i<choice.length; i++){ }}'
          + '{{if( data.defaultChoice == i ){ }}'
          + '<button type="button" class="btn active btn-theme" data-dismiss="modal" data-code="{{=choice[i].code}}" id="choiceBtn{{=choice[i].code}}">{{=choice[i].display}}</button>'
          + '{{ }else{ }}'
          + '<button type="button" class="btn" data-dismiss="modal" data-code="{{=choice[i].code}}" id="choiceBtn{{=choice[i].code}}">{{=choice[i].display}}</button>'
          + '{{ } }}'
          + '{{ } }}'
          + '</div></div></div></div>'
      },{
          type: 'messageDetail',
          template: '{{var data = it;}} <div class="modal fade error-modal" tabindex="-1" role="dialog" aria-hidden="true">'
          + '<div class="modal-dialog">'
          + '<div class="modal-content">'
          + '<div class="modal-header">'
          + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
          + '<h4 class="modal-title"><i class="{{if(data.icon){ }} {{=data.icon}} {{ }else{ }}fa fa-envelope-o{{ } }}"></i>'
          + '<b>{{if(data.title){ }} {{=data.title}} {{ }else{ }} Notice Detail {{ } }}</b></h4>'
          + '</div>'
          + '<div class="modal-body">'
          + '{{if(data.message){ }}<div class="message">{{=data.message}}</div>{{ } }}'
          + '{{if(data.detail) { }}<div class="detail"> {{=data.detail }}</div>{{ } }}'
          + '</div>'
          + '<div class="modal-footer">'
          + '<button type="button" class="btn btn-theme" data-dismiss="modal">Close</button>'
          + '</div></div></div></div>'
      },{
        type: 'messageBox',
        template: '{{var data = it;}}<div class="modal fade error-modal" tabindex="-1" role="dialog" aria-labelledby="u-modal-label" aria-hidden="true">'
          + '<div class="modal-dialog">'
          + '<div class="modal-content">'
          + '<div class="modal-header">'
          + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
          + '<h4 class="modal-title"><i class="{{if(data.icon){ }} {{=data.icon}} {{ }else{ }}fa fa-envelope-o{{ } }}"></i>'
          + '<b>{{if(data.title){ }} {{=data.title}} {{ }else{ }} Message {{ } }}</b></h4>'
          + '</div>'
          + '<div class="modal-body">'
          + '{{if(data.message){ }}<div class="message">{{=data.message}}</div>{{ } }}'
          + '</div>'
          + '<div class="modal-footer">'
          + '<button type="button" class="btn" data-dismiss="modal">Close</button>'
          + '</div></div></div></div>'
      }], // end of templates

    });
  }

  // 修改udeskNotice组件默认值
  if(fn.udeskNotice) {
    jQuery.extend(true, fn.udeskNotice.defaults, {
      // 通知框模板
      templates: [{
        type: 'success',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><h4><b>Success</b></h4>'
          + '#if(message){#<p class="message-brief"><a class="tip" #if(message.length > 19){#title="#=message#"#}#>#=message#</a></p>#}#'
          + '#if(detail && detail.length > 19){#<p class="message-detail"><a data-type="success">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p>#}#'
          + '</div><a class="notice-close fa fa-remove"></a>'
          +'</div>'
      }, {
        type: 'warning',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><h4><b>Warning</b></h4>'
          + '#if(message){#<p class="message-brief"><a class="tip" #if(message.length > 19){#title="#=message#"#}#>#=message#</a></p>#}#'
          + '#if(detail && detail.length > 19){#<p class="message-detail"><a data-type="warning">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p>#}#'
          + '</div><a class="notice-close fa fa-remove"></a></div>'
      }, {
        type: 'error',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><h4><b>Error</b></h4>'
          + '#if(message){#<p class="message-brief"><a class="tip" #if(message.length > 19){#title="#=message#"#}#>#=message#</a></p>#}#'
          + '#if(detail && detail.length > 19){#<p class="message-detail"><a data-type="error">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p>#}#'
          + '</div><a class="notice-close fa fa-remove"></a></div>'
      }, {
        type: 'info',
        template: '<div class="u-notice"><div class="notice-image"></div><div class="notice-message"><div><h4><b>Info</b></h4>'
          + '#if(message){#<p class="message-brief"><a class="tip" #if(message.length > 19){#title="#=message#"#}#>#=message#</a></p>#}#'
          + '#if(detail && detail.length > 19){#<p class="message-detail"><a data-type="info">Click to view detail<i class="fa fa-ellipsis-h"></i></a><span class="hidden">#=detail#</span></p> #} else if(detail) {# <p class="message-detail">#=detail#</p>#}#'
          + '</div><a class="notice-close fa fa-remove"></a></div>'
      }], // end of template

    });
  } // end of if

  // 修改udeskTab组件默认值
  if(fn.udeskTab) {
    jQuery.extend(true, fn.udeskTab.defaults, {
      closeConfirm:{
        title: 'Close Confirm',
        message: 'Sure to close this tab?',
        messageCloseAll: 'Sure to close all the tabs?',
        messageCloseOther: 'Sure to close the other tabs?',
        choice:[{
          display: 'Yes',
          code: 'YES'
        },{
          display: 'No',
          code: 'NO'
        }],
      },//end of closeConfirm*/
      templates: {
        rightClickMenu: '<ul class="udesk-tab-context-menu" style="width:auto">'
          +'<li class="tab-close-current"><span class="fa fa-close"></span>Close current</li>'
          +'<li class="tab-close-all"><span class="fa fa-ban"></span>Close all</li>'
          +'<li class="tab-close-other"><span class="fa fa-times-circle"></span>Close other</li></ul>'
      }
    });
  } // end of if

  // 修改udeskDropDownTree组件默认值
  if(fn.udeskDropDownTree) {
    jQuery.extend(true, fn.udeskDropDownTree.defaults, {
      selector:{
        showItems:"Unfold/Search",
        packItems:"Fold"
      }
    });
  } // end of if

  // 修改udeskStaffSelect组件默认值
  if(fn.udeskStaffSelect) {
    jQuery.extend(true, fn.udeskStaffSelect.defaults, {
      srcTree: {
        messages: {
          loading: 'Loading...',
          requestFailed: 'Request failed.',
          retry: 'Retry'
        }
      },
      dstTree: {
        messages: {
          loading: 'Loading...',
          requestFailed: 'Request failed.',
          retry: 'Retry'
        }
      },
      templates: {
        search: '<div class="form-group clearfix">'
        + '<div class="col-xs-12">'
        + ' <span class="k-textbox k-space-right">'
        + '<input class="form-control u-search" placeholder="Search by name" style="height:27px;">'
        + '<a class="k-icon k-i-search u-search-btn"></a>'
        + '</span></div></div>',
        title: '<i class="fa fa-user"></i><b> Select staffs </b>',
        selAllSrc: '<i class="fa fa-check"></i>Select All',
        deSelAllSrc: '<i class="fa fa-close" style="padding-right: 0px;"></i> Unselect All',
        selAllDst: '<i class="fa fa-check"></i>Select All',
        deSelAllDst: '<i class="fa fa-close" style="padding-right: 0px;"></i> Unselect All',
        confirm: 'OK',
        cancel: 'Cancel'
      }
    });
  } // end of if

  // 修改udeskCapitalsTip组件默认值
  if(fn.udeskCapitalsTip) {
    jQuery.extend(true, fn.udeskCapitalsTip.defaults, {
      content: 'Caps Lock is on!',
    });
  } // end of if

  // UDesk public properties and methods
  return udesk;

}));

/***/ })

/******/ });