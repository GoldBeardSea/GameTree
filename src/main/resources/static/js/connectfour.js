var table = document.getElementById("table");
table.addEventListener("click", tableclicked);

function tableclicked(event) {
  let id = event.target.parentElement.id;
  let column = id.charAt(1)-1;
  console.log(column);

  $.ajax('http://localhost:8080/play?' + $.param({column: column}), {
    method: 'POST',
    data: {
        column: column
    }
  }).then(drawboard)
}

function drawboard(board) {
  console.log('board:', board);
  // board is a [6][7] array of integers.
  for (let row = 0; row < 6; row++) {
    for (let column = 0; column < 7; column++) {
      let string = "c" + (column + 1) + "r" + (row + 1);

      let newtd = document.getElementById(string);

      if (board[row][column] === 1) {
        newtd.style.backgroundColor="red";
      } else if (board[row][column] === -1) {
        newtd.style.backgroundColor="yellow";
      }
    }
  }
}

drawboard(gameArray)