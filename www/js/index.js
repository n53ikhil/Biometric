function onLoad() {
	document.addEventListener("deviceready", onDeviceReady, false);
}

function onDeviceReady() {

}

// Initialize view
document.getElementById("btnInfo").addEventListener("click", onDeviceInfo,
		false);
document.getElementById("btnCapture").addEventListener("click", onCapture,
		false);
document.getElementById("btnIrisInfo").addEventListener("click",
		onIrisDeviceInfo, false);
document.getElementById("btnIrisCapture").addEventListener("click",
		onIrisCapture, false);

var lblstatus = document.getElementById("lblstatus");

var btnClick = "";

// Called Functions
function onDeviceInfo() {
	btnClick = "finger_deviceInfo";
	rdsample.finger_deviceInfo("finger_deviceInfo", success, failed);
}

function onCapture() {
	btnClick = "finger_capture";
	rdsample.finger_capture("finger_capture", success, failed);
}

function onIrisDeviceInfo() {
	btnClick = "iris_deviceInfo";
	rdsample.iris_deviceInfo("iris_deviceInfo", success, failed);
}

function onIrisCapture() {
	btnClick = "iris_capture";
	rdsample.iris_capture("iris_capture", success, failed);
}

// Response Data
function success(response) {
	// alert("success: " + response.toString());
	switch (btnClick) {
	case "finger_deviceInfo":
		var info = "";
		info += "\nRDService Info= \n" + response.rd_service_info + "\n\n";
		info += "Capture Info= \n" + response.device_info;
		lblstatus.innerText = info;
		break;
	case "finger_capture":
		lblstatus.innerText = "\nPID Data= \n" + response.pid_data;
		break;
	case "iris_deviceInfo":
		var info = "";
		info += "\nRDService Info= \n" + response.rd_service_info + "\n\n";
		info += "Capture Info= \n" + response.device_info;
		lblstatus.innerText = info;
		break;
	case "iris_capture":
		lblstatus.innerText = "\nPID Data= \n" + response.pid_data;
		break;
	default:
		break;
	}
}

function failed(error) {
	// alert("failed: " + error);
	lblstatus.innerText = error;
}
