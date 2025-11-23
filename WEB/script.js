class Cell{
    constructor(pos){
        this.el = $('<div>');
        this.el.addClass('snakeBody');
    }
}

let pos = $("#grid")[0].getBoundingClientRect();
console.log(pos);

$(document).on('keydown',(e)=>{
    // console.log()
    if(e.keyCode === 37) console.log("LEFT");
    if(e.keyCode === 38) console.log("UP");
    if(e.keyCode === 39) console.log("RIGHT");
    if(e.keyCode === 40) console.log("DOWN");
})

head = new Cell(pos);
// head.el.