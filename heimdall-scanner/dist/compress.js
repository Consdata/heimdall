"use strict";
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
Object.defineProperty(exports, "__esModule", { value: true });
var util_1 = require("./util");
function compressJson(obj) {
    if (util_1.isObject(obj)) {
        return Object.keys(obj)
            .reduce(function (result, key) {
            var _a;
            return (__assign({}, result, (_a = {}, _a[key.substring(0, 1)] = compressJson(obj[key]), _a)));
        }, {});
    }
    else if (util_1.isArray(obj)) {
        return obj.map(function (e) { return compressJson(e); });
    }
    else {
        return obj;
    }
}
exports.compressJson = compressJson;
