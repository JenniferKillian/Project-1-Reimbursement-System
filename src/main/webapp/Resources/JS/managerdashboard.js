window.onload = function(){

let cs = new XMLHttpRequest();
	
	cs.onreadystatechange = function(){
		user =JSON.parse(cs.responseText)
			if (user == null){
				window.location.href="Resources/HTML/unauthorized.html";
			} else if (user.userType.role == "finance manager"){
			
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
	r.open("POST", "http://localhost:8080/project-1/managerdashGetAll.json");
	r.send();
 
 	

}

function DOMManipulation(requestList){

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
	let cell7 = row.insertCell(6);
	let cell8 = row.insertCell(7);
	let cell9 = row.insertCell(8);
	
	cell1.innerHTML = "Pending";
	if (requestList[i].status == 1){
		cell1.innerHTML = "Approved";
	} else if (requestList[i].status == 0){
		cell1.innerHTML = "Rejected";
	}
	cell2.innerHTML = requestList[i].amount;	
	cell3.innerHTML = requestList[i].description;
	cell4.innerHTML = requestList[i].type.type;
	cell5.innerHTML = requestList[i].author.id;
	
	cell6.innerHTML = requestList[i].submitted.month + " " + requestList[i].submitted.dayOfMonth  + " " +  requestList[i].submitted.year;
	if(requestList[i].resolved){
		cell7.innerHTML = requestList[i].resolved.month + " " + requestList[i].resolved.dayOfMonth + " " + requestList[i].resolved.year;
	}
	
	
	
    if(requestList[i].author.id == user.id){
    	
    } else {
    	var form = document.createElement("form"); 
    	form.setAttribute("method", "post");
    	form.setAttribute("action", "/project-1/approve.json");var id = document.createElement("input");
    	id.setAttribute("name", "id");
    	id.setAttribute("value", requestList[i].reimbId);
    	id.setAttribute("type", "hidden");
    	form.appendChild(id);
    	var s = document.createElement("input"); 
    	s.setAttribute("type", "submit"); 
    	s.setAttribute("value", "Approve");
    	form.appendChild(s);
    	cell8.appendChild(form);
    }
    
    
    var formD = document.createElement("form"); 
    formD.setAttribute("method", "post"); 
    formD.setAttribute("action", "/project-1/deny.json");
    var idD = document.createElement("input");
    idD.setAttribute("name", "id");
    idD.setAttribute("value", requestList[i].reimbId);
    idD.setAttribute("type", "hidden");
    formD.appendChild(idD);
    var sD = document.createElement("input"); 
    sD.setAttribute("type", "submit"); 
    sD.setAttribute("value", "Deny");
    formD.appendChild(sD);
    cell9.appendChild(formD);
    

}}


function filter(value){
var table = document.getElementById("reimTable");
if (value == "All"){
	for (var i = 1, row; row = table.rows[i]; i++) {
		row.style.display = "table-row";
	}
}
if (value == "Pending"){
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML == "Pending"){
			row.style.display = "table-row";
		} else {
			row.style.display = "none";
		}
	}
}
if (value == "Approved"){
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML == "Approved"){
			row.style.display = "table-row";
		} else {
			row.style.display = "none";
		}
	}
}
if (value == "Rejected"){
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML == "Rejected"){
			row.style.display = "table-row";
		} else {
			row.style.display = "none";
		}
	}
}



}




















