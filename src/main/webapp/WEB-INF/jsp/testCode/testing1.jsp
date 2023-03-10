<!DOCTYPE html>
<html>
<head>
 <title>quiz in Javascript </title>
 <link rel="stylesheet" href="style.css">
<style>
body{
 margin:0;
 background-color:#d9cdcd;
 font-family: sans-serif;
}
*{
 box-sizing: border-box;
}

.quiz-container{
 max-width: 700px;
 min-height:500px;
 background-color: #ffffff;
 margin:40px auto;

 border-radius:10px;
 padding:30px;
}
.quiz-container::after,.quiz-container::before{
 content: '';
 clear: both;
 display: table;

}
.question-number,
.question,
.options,
.button,
.answers-tracker{
 float: left;
 width: 100%;
}
.question-number h3{
 color:#009688;
 border-bottom: 1px solid #ccc;
 margin:0;
 padding-bottom:10px;
}

.question {
 font-size:22px;
 color:000000;
 padding:20px 0;

}

.options div{
  background-color: #cccccc;
  font-size:16px;
  color:000000;
  margin-bottom:10px;
  border-radius:5px;
  padding:15px;
  position: relative;
  overflow:hidden;
  cursor: pointer;
}
.options div.disabled{
 pointer-events: none;
}
.options div.correct{
 z-index: 1;
 color: #fff;
}
.options div.correct::before{
 content: '';
 position: absolute;
 left:0;
 top:0;
 width: 100%;
 height: 100%;
 background-color: green;
 z-index: -1;

 animation: animateBackground 1s ease;
 animation-fill-mode: forwards;
}
@keyframes animateBackground{
 0%{
  transform: translateX(-100%);
 }
 100%{
  transform: translateX(0%);
 }
}
.options div.wrong{
 z-index: 1;
 color: #fff;
}
.options div.wrong::before{
 content: '';
 position: absolute;
 left:0;
 top:0;
 width: 100%;
 height: 100%;
 background-color:red;
 z-index: -1;

 animation: animateBackground 1s ease;
 animation-fill-mode: forwards;
}
@keyframes animateBackground{
 0%{
  transform: translateX(-100%);
 }
 100%{
  transform: translateX(0%);
 }
}

.button .btn{
 padding:15px 50px;
 border-radius:5px;
 cursor: pointer;
 background-color:#009688;
 font-size:16px;
 color:#ffffff;
 border: none;
 display: inline-block;
 margin:15px 0 20px;
}
.answers-tracker{
 border-top:1px solid #ccc;
 padding-top: 15px;
}
.answers-tracker div.correct{
 background-color: green;
 background-image:url('img/correct.png');
 background-position: center;
 background-repeat: no-repeat;
 background-size: 50%;

}
.answers-tracker div.wrong{
 background-color: red;
 background-image:url('img/wrong.png');
 background-position: center;
 background-repeat: no-repeat;
 background-size: 50%;
}

.quiz-over{
 position: fixed;
 left:0;
 top:0;
 width: 100%;
 height: 100%;
 background-color:rgba(0,0,0,0.9);
 z-index: 10;
 display: none;
 align-items: center;
 justify-content: center;
}
.quiz-over.show{
 display: flex;
}
.quiz-over .box{
 background-color: #009688;
 padding:30px;
 border-radius:10px;
 text-align: center;
 flex-basis: 700px;
 max-width:700px;
 color:#ffffff;
}

.quiz-over .box h1{
 font-size:36px;
 margin:0 0 20px;
}

.quiz-over .box button{
 padding:15px 50px;
 border:none;
 background-color:#FF9800;
 border-radius:5px;
 font-size:16px;
 margin:15px 0 20px;
 color:#ffffff;
}

</style>
</head>
<body>
     <div class="quizContainer container-fluid well well-lg">
        <div id="quiz1" class="text-center">
   <h3 style="color:#d14040;position:center;left:80%;top:0%;" align="Center" ><span id="Modern History Of India Quiz part 01 for all Exams">Quiz for all Exams </span><br/><span id='Modern History Of India Quiz part 01 for all Exams' style="font-size:25px;"></span></h3>
   <h2 Style="color:#ff000;position:center;left:80%;top0%;" align="center" "font-size:25px;"><samp id="Sub heading"> Test Quiz</samp></h2>
         
  </div>
 <div class="quiz-container">
    <div class="question-number">
       <h3>Question <span class="question-num-value"></span> of <span class="total-question"></span></h3>
    </div>
    <div class="question">
   
    </div>
    <div class="options">
       <div id="1" class="option1" onclick="check(this)"></div>
       <div id="2" class="option2" onclick="check(this)"></div>


       <div id="3" class="option3" onclick="check(this)"></div>


       <div id="4" class="option4" onclick="check(this)"></div>
    </div>
    <div class="button">
       <button type="button" class="btn" onclick="next()">Next</button>
    </div>
    <div class="answers-tracker">
     
    </div>
 </div>

 <div class="quiz-over">
   <div class="box">
     <h1>
       Good Try!<br>
      You Got <span class="correct-answers"></span> out of <span class="total-question2"></span> answers correct! <br>
      That's <span class="percentage"></span>
     </h1>
     <button type="button" onclick="tryAgain()">TryAgain</button>
   </div>
 </div>

 <script src="script.js"></script>
<script>

  const options=document.querySelector(".options").children;
  const answerTrackerContainer=document.querySelector(".answers-tracker");
  const questionNumberSpan=document.querySelector(".question-num-value");
  const totalQuestionSpan=document.querySelector(".total-question");
  const correctAnswerSpan=document.querySelector(".correct-answers");
  const totalQuestionSpan2=document.querySelector(".total-question2");
  const percentage=document.querySelector(".percentage");


  const question=document.querySelector(".question");
  const op1=document.querySelector(".option1");
  const op2=document.querySelector(".option2");
  const op3=document.querySelector(".option3");
  const op4=document.querySelector(".option4");
  let questionIndex;
  let index=0;
  let myArray=[];
  let myArr=[];
  let score=0;

  // questions and options and answers

  const questions=[
	   {
		    q:'where is the capital of India',
		    options:['New Delhi','Kolkatta','Varanashi','Agra'],
		    answer:1
		   },
		   {
		    q:'Who is the Prime Minister of India',
		    options:['Amit Shah','Narendra Modi','Rahul Gandhi','None of the above'],
		    answer:2
		   },
		   {
		    q:'where is the capital of Rajasthan',
		    options:['Kota','Jaipur','Ajmer','Jhunjhunu'],
		    answer:2
		   },
		   {
		    q:'Who is the CM of Rajasthan',
		    options:['Ashok Gahlot','Vasundra Raje','Hanuman Beniwal','Sachin Pilet'],
		    answer:1
		   },
		   {
		    q:'where is the capital of HR',
		    options:['New Delhi','Kolkatta','Chandigade','Agra'],
		    answer:3
		   },
		   {
		    q:'where is the Amer Ford',
		    options:['Sikar','Churu','Jaipur','Jhunjhunu'],
		    answer:2
		   }
		 
  ]

  // set questions and options and question number
  totalQuestionSpan.innerHTML=questions.length;
  function load(){
        questionNumberSpan.innerHTML=index+1;
         question.innerHTML=questions[questionIndex].q;   
         op1.innerHTML=questions[questionIndex].options[0];
         op2.innerHTML=questions[questionIndex].options[1];
         op3.innerHTML=questions[questionIndex].options[2];
         op4.innerHTML=questions[questionIndex].options[3];
         index++;
  }

  function check(element){
   if(element.id==questions[questionIndex].answer){
    element.classList.add("correct");
    updateAnswerTracker("correct")
    score++;
    console.log("score:"+score)
   }
   else{
    element.classList.add("wrong");
    updateAnswerTracker("wrong")

   }
   disabledOptions()
  }
 
  function disabledOptions(){
     for(let i=0; i<options.length; i++) {
      options[i].classList.add("disabled");
      if(options[i].id==questions[questionIndex].answer){
       options[i].classList.add("correct");
      }

     }
  }

  function enableOptions(){
     for(let i=0; i<options.length; i++) {
      options[i].classList.remove("disabled","correct","wrong");
     }
  }

  function validate(){
      if(!options[0].classList.contains("disabled")){
        alert("Please Selecto one option")
      }
      else{
       enableOptions();
       randomQuestion();
      }
  }

  function next(){
    validate();
  }
 
  function randomQuestion(){
   let randomNumber=Math.floor(Math.random()*questions.length);
   let hitDuplicate=0;
       if(index==questions.length){
        quizOver();
       }
       else{
         if(myArray.length>0){
             for(let i=0; i<myArray.length; i++){
               if(myArray[i]==randomNumber){
                  hitDuplicate=1;
                  break;
               }
             }
             if(hitDuplicate==1){
              randomQuestion();
             }
             else{
               questionIndex=randomNumber; 
              load();
              myArr.push(questionIndex);
             }
         }
         if(myArray.length==0){
           questionIndex=randomNumber; 
           load();
           myArr.push(questionIndex);
         }

       myArray.push(randomNumber);
     
      }
  }
   
  function answerTrakcer(){
     for(let i=0; i<questions.length; i++){
      const div=document.createElement("div")
         answerTrackerContainer.appendChild(div);
     }
  }

 function updateAnswerTracker(classNam){
   answerTrackerContainer.children[index-1].classList.add(classNam);
 }

 function quizOver(){
    document.querySelector(".quiz-over").classList.add("show");
    correctAnswerSpan.innerHTML=score;
    totalQuestionSpan2.innerHTML=questions.length;
    percentage.innerHTML=(score/questions.length)*100 + "%";
 }

 function tryAgain(){
     window.location.reload();
 }

 window.onload=function(){
  randomQuestion();
  answerTrakcer();

}

</script>

</body>
</html>