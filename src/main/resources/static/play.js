'use strict'

var table = document.getElementById("table");
table.addEventListener("click", tableclicked);

function tableclicked(event){
  let id = event.target.id;
  let column = id.charAt(1)-1;
  console.log(column);
}