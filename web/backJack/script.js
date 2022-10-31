let firstcard , secondcard , sum=0
let hasBlackJack=false ,isalive=false
let message=""


let cardnum=["","ace","two","three","four","five","six","seven","eight","nine","jack","queen","king"]
let cardval=[0,11,2,3,4,5,6,7,8,9,10,10,10]

let mes=document.querySelector("#message-el")
let player={
   playername: "Aagam",
   chips: 200
}

document.querySelector("#player-id").textContent=player.playername+" :$"+player.chips
function startgame()
{
   isalive=true
   hasBlackJack=false
   firstcard = (Math.floor(Math.random()*12)+1)
   secondcard = (Math.floor(Math.random()*12)+1)
   sum=cardval[firstcard] + cardval[secondcard]
   document.getElementById("sum-el").textContent="Sum : "+sum
   document.getElementById("card-el").textContent="Cards : "+cardnum[firstcard]+" - "+cardnum[secondcard]
   if(firstcard===1 && secondcard===1)
   {
      message="You get new card \""+cardnum[secondcard]+"\" and your sum becomes \""+sum+"\" so you are out of game."
      mes.textContent=message
      document.getElementById("sum-el").textContent="Re-start the game !"
      isalive=false
      return
   }

   check()
}

let newc;
function newCard()
{
   if(!isalive || hasBlackJack)
    return
   newc = (Math.floor(Math.random()*12)+1)
   sum+=cardval[newc]
   document.getElementById("sum-el").textContent="Sum : "+sum
   document.getElementById("card-el").textContent+=" - "+cardnum[newc]
   check()
}

function check()
{
   if(sum<21)
   {
      message="Do you want to draw a new Card ?"
   }
   else if(sum === 21)
   {
      message="🎉 🎉  Congratulations , \"BlackJack\" 🎉 🎉 !"
      hasBlackJack=true
   }
   else
   {
      message="You get new card \""+cardnum[newc]+"\" and your sum becomes \""+sum+"\" so you are out of game."
      document.getElementById("card-el").textContent= "⚠️"
      document.getElementById("sum-el").textContent="Re-start the game !"
      isalive=false
   }
   mes.textContent=message
}


let c=0
function infomes()
{
   if(c === 0)
   {
      document.querySelector(".rule").textContent=" "+"Rules: 1) if Sum of cards = 21 (You get BlackJack) 2) if Sum of cards < 21 (You get extra chance to choose card) 3) else you loose the game."
      c=1
   }
   else
   {
      document.querySelector(".rule").textContent=""
      c=0
   }
}

