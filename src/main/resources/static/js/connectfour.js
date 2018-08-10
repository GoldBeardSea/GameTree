var table = document.getElementById("table");
table.addEventListener("click", tableclicked);
playing=true;
var button = document.getElementById("NewGame");
button.addEventListener("click", newgameclicked);

function newgameclicked(event) {
  $.ajax('/newgame', {
    method: 'POST',
    data: {
    }
  }).then(window.location.href="/play")
}


function tableclicked(event) {
  let id = event.target.parentElement.id;
  let column = id.charAt(1)-1;
  console.log(column);

  $.ajax('/play?' + $.param({column: column}), {
    method: 'POST',
    data: {
        column: column
    }
  }).then(drawboard)
}

function drawboard(board) {
  console.log(playing);
  if (!playing){
  board = [[]];
  }

  console.log('board:', board);
  // board is a [6][7] array of integers.
  for (let row = 0; row < board.length; row++) {
    for (let column = 0; column < board[0].length; column++) {
      let string = "c" + (column + 1) + "r" + (row + 1);

      let newtd = document.getElementById(string);

      if (board[row][column] === 1) {
        newtd.style.backgroundColor="red";
      } else if (board[row][column] === -1) {
        newtd.style.backgroundColor="black";
      }
    }
  }
}

drawboard(gameArray)