/*global cordova, module*/

module.exports = {
	finger_deviceInfo : function(name, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RDSample",
				"finger_deviceInfo", [ name ]);
	},
	finger_capture : function(name, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RDSample",
				"finger_capture", [ name ]);
	},
	iris_deviceInfo : function(name, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RDSample",
				"iris_deviceInfo", [ name ]);
	},
	iris_capture : function(name, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RDSample",
				"iris_capture", [ name ]);
	}
};
