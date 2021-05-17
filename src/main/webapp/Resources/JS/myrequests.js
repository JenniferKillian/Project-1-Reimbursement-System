/**
 * 
 */
 window.onload = function(){
 	let cs = new XMLHttpRequest();
	cs.onreadystatechange = function(){
		user =JSON.parse(cs.responseText)
		if (user == null){
				window.location.href="Resources/HTML/unauthorized.html";
			} else if (user.userType.role == "employee"){
			
			} else {
				window.location.href="Resources/HTML/unauthorized.html";
			}
	}
	cs.open("POST", "http://localhost:8080/project-1/checkSession.json");
	cs.send();

 
 	let r = new XMLHttpRequest();
	
	r.onreadystatechange = function(){
		if(r.readyState==4 && r.status==200){
			requestList =JSON.parse(r.responseText)
			console.log(requestList);
			DOMManipulation(requestList);
		}
	}
	r.open("POST", "http://localhost:8080/project-1/getByUser.json");
	r.send();

}

function DOMManipulation(requestList){
var id = requestList[0].author.id;
console.log(id);
document.getElementById("id").value = id;

var i;
for (i = 0; i < requestList.length; i++) {
	let table = document.getElementById("tableBody");
	let row = table.insertRow(i);
	let cell1 = row.insertCell(0);
	let cell2 = row.insertCell(1);
	let cell3 = row.insertCell(2);
	let cell4 = row.insertCell(3);
	let cell5 = row.insertCell(4);
	let cell6 = row.insertCell(5);
	
	cell1.innerHTML = "Pending";
	if (requestList[i].status == 1){
		cell1.innerHTML = "Approved";
	} else if (requestList[i].status == 0){
		cell1.innerHTML = "Rejected";
	}
	cell2.innerHTML = requestList[i].amount;	
	cell3.innerHTML = requestList[i].description;
	cell4.innerHTML = requestList[i].type.type;
	cell5.innerHTML = requestList[i].submitted.month + " " + requestList[i].submitted.dayOfMonth  + " " +  requestList[i].submitted.year;
	if(requestList[i].resolved){
		cell6.innerHTML = requestList[i].resolved.month + " " + requestList[i].resolved.dayOfMonth + " " + requestList[i].resolved.year;
	}
}}
 
