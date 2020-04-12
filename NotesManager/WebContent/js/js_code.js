$(document).ready(function() {
	   getByAuthor();
	});
	
var data={"sample":"json"}
  
  function ajaxreq(){
	  $.ajax({
			'type':'post',
			'url':'https://jsonplaceholder.typicode.com/posts',
			'contentType':'application/json',
			'data':JSON.stringify(data),
			'dataType':'json',
			
			}).done(function(i){console.log(i);});
  }

function getCookie(cname) {
	  var name = cname + "=";
	  var decodedCookie = decodeURIComponent(document.cookie);
	  var ca = decodedCookie.split(';');
	  for(var i = 0; i <ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}

function getByAuthor(){
	
	var username = getCookie("username");
	
	var session_id = getCookie("session_id");
	
	$("#welcome-note").html("Welcome "+username+", find all your notes here!")
	
	var getByAuthorRequest = {"author":username,"sessionId":session_id,"username":username};
	console.log(getByAuthorRequest);
	
	
	$.ajax({
		'type':'post',
		'url':'http://localhost:8080/nm/resources/notes/getByAuthor',
		'contentType':'application/json',
		'data':JSON.stringify(getByAuthorRequest),
		'dataType':'json'
		
		}).done(function(i){populateNotes(i);});
}



function populateNotes(notes){
	console.log(notes);
	var notesdiv = $("#notesdiv");
	for(i=0;i<Object.keys(notes).length;i++){
		notesdiv.append('<div class="col-md-4" id="'+notes[i].noteAgn+'"><div class="card mb-4 shadow-sm"><div class="card-body"><p class="card-text">'+notes[i].note+'<div class="d-flex justify-content-between align-items-center"><div class="btn-group"><button type="button" class="btn btn-sm btn-outline-secondary" id="d'+notes[i].noteAgn+'" onclick="deleteNote(this.id)">Delete</button><button type="button" class="btn btn-sm btn-outline-secondary">Edit</button></div><small class="text-muted">9 mins</small></div></div></div></div>');
	}
}

function logOut(){
	document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	document.cookie = "session_id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	window.location.href = '/nm/deletecookie';
}


function addNoteOnClick(){
	
	$("#addNoteDialogButton").attr("disabled", true);
	
	
	console.log($("#textAreaToAddNote").val());
	
var username = getCookie("username");
	
	var session_id = getCookie("session_id");
	
	var note = $("#textAreaToAddNote").val();
	
	var addNoteRequest = {"author":username,"sessionId":session_id,"username":username,"note":note};
	console.log(addNoteRequest);
	
	$.ajax({
		'type':'post',
		'url':'http://localhost:8080/nm/resources/notes/add',
		'contentType':'application/json',
		'data':JSON.stringify(addNoteRequest),
		'dataType':'json',
		'success':function(data){
			updateAddNote(addNoteRequest,data);
		}
		
		});
	
}



function updateAddNote(note,data){
	$(function () {
		   $('#exampleModal').modal('toggle');
		});
	var notesdiv = $("#notesdiv");
	notesdiv.append('<div class="col-md-4" id="'+data.agn+'"><div class="card mb-4 shadow-sm"><div class="card-body"><p class="card-text">'+note.note+'<div class="d-flex justify-content-between align-items-center"><div class="btn-group"><button type="button" class="btn btn-sm btn-outline-secondary" id="d'+data.agn+'" onclick="deleteNote(this.id)">Delete</button><button type="button" class="btn btn-sm btn-outline-secondary">Edit</button></div><small class="text-muted">9 mins</small></div></div></div></div>');
	
	$("#addNoteDialogButton").removeAttr("disabled");
	$("#textAreaToAddNote").val("");
}

function deleteNote(deleteNoteEle){
	
	var noteagn = deleteNoteEle.substring(1);
	
var username = getCookie("username");
	
	var session_id = getCookie("session_id");
	console.log(noteagn);
	var deleteNoteRequest = {"noteAgn":noteagn,"sessionId":session_id,"username":username};
	
	var agn = $.ajax({
		'type':'post',
		'url':'http://localhost:8080/nm/resources/notes/delete',
		'contentType':'application/json',
		'data':JSON.stringify(deleteNoteRequest),
		'dataType':'json',
		'success':updateDeleteNote(noteagn)
		
		});
}

function updateDeleteNote(agn){
	
	$("#"+agn).remove();
}