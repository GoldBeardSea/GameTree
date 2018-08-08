'use strict'

var table = document.getElementById("table");
table.addEventListener("click", tableclicked);

function tableclicked(event){
  let id = event.target.id;
  let column = id.charAt(1)-1;
  console.log (id);
}

function drawpiece(row, column, color){
  column++;
  row++;
  let string = "c" + column + "r" + row;
  let newtd = document.getElementById(string);

  if (color == "red") {
    newtd.textContent = "X";
  } else if (color == "black") {
    newtd.textContent = "O";
  }
}