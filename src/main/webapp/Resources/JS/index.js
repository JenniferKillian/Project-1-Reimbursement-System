/**
 * 
 */
window.onload = function(){
	
	sessionStorage.clear();
}








function setCacheLoggedIn(swJSON){
	//document.getElementById("swName").innerText = `Name: ${swJSON.name}`;
	//document.getElementById("swBirthYear").innerText = `Birth Year: ${swJSON.birth_year}`;
}


let promise = new Promise(function(resolve,reject){
	//const x =5;
	//const y =8;
	if(x >=y){
		resolve();
	}else{
		reject();
	}
	
});
promise.then(function(){
	console.log('We passed!');
}).catch(function(){
	console.log('Whoops');
})
