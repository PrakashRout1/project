function rpsgame(yourChoice){
    var humanChoice,botChoice;
    humanChoice=yourChoice.id;
    console.log(yourChoice.id);
    botChoice=numberChoice(randNumber());
    console.log('Computer choice:',botChoice);

   results=decideWinner(humanChoice,botChoice);//[0,1]
    console.log(results);

    message=finalMessage(results);//{'message':'You won!',color:'green'}
    console.log(message);

    rpsFrontEnd(yourChoice.id,botChoice,message);
}

function randNumber(){
    return Math.floor(Math.random()*3);
}

function numberChoice(number){
    return ['rock','paper','scissors'][number];
}

function decideWinner(yourChoice,computerChoice){
    var rpsDatabase={
        'rock':{'scissors':1,'rock':0.5,'paper':0},
        'paper':{'scissors':0,'rock':1,'paper':0.5},
        'scissors':{'scissors':0.5,'rock':0,'paper':1}
    }
    var yourScore=rpsDatabase[yourChoice][computerChoice];
    var computerScore=rpsDatabase[computerChoice][yourChoice];

    return [yourScore,computerScore];
}

function finalMessage([yourScore,computerScore]){
    if(yourScore=== 0){
        return {'message':'You Lost!','color':'red'};
    }
    else if(yourScore===0.5){
        return {'message':'Tied!','color':'Yellow'};
    }
    else{
        return {'message':'You Won!','color':'green'};
    }
}

function rpsFrontEnd(humanImageChoice,BotImageChoice,finalMessage){
    var imagesDatabase={
        'rock':document.getElementById('rock').src,
        'paper':document.getElementById('paper').src,
        'scissors':document.getElementById('scissors').src
    }

    //lets remove all the images
    document.getElementById('rock').remove();
    document.getElementById('paper').remove();
    document.getElementById('scissors').remove();

    var humanDiv=document.createElement('div');
    var botDiv=document.createElement('div');
    var messageDiv=document.createElement('div');

    humanDiv.innerHTML="<img src='"+imagesDatabase[humanImageChoice]+"' height=200 width=200 style='box-shadow: 0px 10px 50px rgba(37, 50,233, 1)'>";
    messageDiv.innerHTML="<h1 style='color:"+finalMessage['color']+";font-size:60px;pading:30px'>"+finalMessage['message']+"</h1>";
    botDiv.innerHTML="<img src='"+imagesDatabase[BotImageChoice]+"' height=200 width=200 style='box-shadow: 0px 10px 50px rgba(243, 38,24, 1)'>";

    document.getElementById('flex-box-rps-div').appendChild(humanDiv);
    document.getElementById('flex-box-rps-div').appendChild(messageDiv);
    document.getElementById('flex-box-rps-div').appendChild(botDiv);
    var butDiv=document.createElement('div');
    butDiv.innerHTML="<a href='index.html' class='btn btn-success'>Play again</a>";
    document.getElementById('button').appendChild(butDiv);
}