let gender = "";
let url = "/lee/chataverse/choiceGender/";
$("#btnMan").click(function(){
	console.log("남자");
	gender = "man";
	url = url + gender;
	window.location.href = url;
});
$("#btnWoman").click(function(){
	console.log("여자");
	gender = "woman";
	url = url + gender;
	window.location.href = url;
});