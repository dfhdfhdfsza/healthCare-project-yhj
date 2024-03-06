let userNO = document.getElementById("userNO").value; // 유저번호
let selectedDateInput = document.getElementById('selectedDateInput'); // 날짜 선택하면 날짜 넣어줄 값
let today = new Date();
let searchBOxSwitch = true;  
// 연, 월, 일을 추출하기
let year = today.getFullYear();
let month = today.getMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.
let day = today.getDate();

// 위에서 추출한 값을 이용하여 원하는 형식으로 날짜 표시
let formatToday = `${year}-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
//모달const loremIpsum = document.getElementById("lorem-ipsum")
    fetch("https://baconipsum.com/api/?type=all-meat&paras=200&format=html")
        .then(response => response.text())
        .then(result => loremIpsum.innerHTML = result)
    const modal = document.getElementById("modal")
    function modalOn() {
        modal.style.display = "flex"
    }
    function isModalOn() {
        return modal.style.display === "flex"
    }
    function modalOff() {
        modal.style.display = "none"
    }
    const btnModal = document.getElementById("btn-modal")
    const closeBtn = modal.querySelector(".close-area")
    closeBtn.addEventListener("click", e => {
        modalOff()
    })
    modal.addEventListener("click", e => {
        const evTarget = e.target
        if(evTarget.classList.contains("modal-overlay")) {
            modalOff()
        }
    })
    window.addEventListener("keyup", e => {
        if(isModalOn() && e.key === "Escape") {
            modalOff()
        }
    })

selectedDateInput.value = formatToday;
spreadEatFood();
spreadSelectDayNutrition(formatToday);
// full-calendar 생성하기

// 로그인 안하면 튕겨내기
if (userNO == null || userNO == "") {
    alert("로그인 후 이용해주세요");
    location.href = "/";
}

/** 선택한 날짜 먹음 은식 뿌려주는 이벤트 */
function spreadEatFood(){
    //document.getElementById("selectDate").innerText = selectedDateInput.value;
    for(let i = 0 ; i < getUserEatFoodName.length ; i++){
            
        if ( selectedDateInput.value ==getUserEatFoodName[i].date) {
        document.getElementById(getUserEatFoodName[i].eatTime).innerHTML +=`<div> ${getUserEatFoodName[i].processedFoodItemName}</div>`;
        }
    }
    
}

/** 검색하면 비동기로 음식 리스트 가져오기 */
async function findFood() {
    var keyword = document.getElementById("keywordInput").value;
    let searchResult = document.getElementById('serchresult');
    let ResultHTML = "";
    try {
        const url = `/food/findFood?keyword=${encodeURIComponent(keyword)}`;
        const config = {
            method: 'GET',
            headers: {
                'Content-Type': 'text/plain'
            }
        };
        const resp = await fetch(url, config);
        const result = await resp.json();
        searchResult.innerHTML = "";
        if (keyword != "") {
            for (let i = 0; i < result.length; i++) {
                console.log(result[i]);
                ResultHTML += `<div>${result[i].processedFoodItemName}<button popovertarget='food${i}'>+</button></div>`;
                ResultHTML += `<div id='food${i}' class='food-popover'  popover> ${result[i].processedFoodItemName}`;
                ResultHTML += `<form action="/food/checkFood"  method="post">`;
                ResultHTML += `<input type='hidden' name='energyKcal' value='${result[i].energyKcal}' /> `;
                ResultHTML += `<input type='hidden' name='carbohydrate' value='${result[i].carbohydrate}' /> `;
                ResultHTML += `<input type='hidden' name='protein' value='${result[i].protein}' /> `;
                ResultHTML += `<input type='hidden' name='fat' value='${result[i].fat}' /> `;
                ResultHTML += `<input type='hidden' name='userNo' value='${userNO}' /> `;
                ResultHTML += `<input type='hidden' name='processedFoodItemName' value='${result[i].processedFoodItemName}' /> `;
                ResultHTML += `<input type='hidden' name='date' value='${selectedDateInput.value}' /> `;
                ResultHTML += `칼로리 :  ${result[i].energyKcal}`;
                ResultHTML += `탄 :  ${result[i].carbohydrate}`;
                ResultHTML += `단 : ${result[i].protein} `;
                ResultHTML += `지 : ${result[i].fat} <br>`;
                ResultHTML += `  <input type='radio' name='eatTime' value='breakfast' />아침`;
                ResultHTML += `  <input type='radio' name='eatTime' value='lunch' />점심`;
                ResultHTML += `  <input type='radio' name='eatTime' value='dinner' />저녁`;
                ResultHTML += `  <input type='radio' name='eatTime' value='dessert' />간식`;
                ResultHTML += `  <button type='submit'>+</button></form> </div>`;
            }
            searchResult.innerHTML += ResultHTML;
        }

    } catch (error) {
        console.error(error);
    }
}

// 선택한 날짜 먹은 영양정보 가져오기
async function getSelectDayNutritionInfo(date) {
    try {
        const url = `/food/getNutitionInfo?date=${date}`;
        const config = {
            method: 'GET',
            headers: {
                'Content-Type': 'text/plain'
            }
        };
        const resp = await fetch(url, config);
        const result = await resp.json();

    } catch (error) {
        console.error(error);
    }
}

// calendar element 취득
let calendarEl = $('#calendar')[0];

let selectDate;// 현재 날짜를 가져오기

let selectedDateInfo;

let selectOn = false;

let calendar = new FullCalendar.Calendar(calendarEl, {

    height: '300px', //calendar 높이 설정
    aspectRatio: 0.6,
    expandRows: true, //화면에 맞게 높이 재설정
    customButtons: {
        myCustomButton: {
          text: '식단추가',
          click: function() {

            if(selectOn==true){
              modal.style.display = "flex";

           }else{
            alert("날짜를 선택해주세요");
           }
         
          }
        }
      },
    headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'myCustomButton'
    },
    initialView: 'dayGridWeek', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
    visibleRange: {
        start: '2022-01-01', // 해당 주의 시작 날짜
        end: '2022-01-07'    // 해당 주의 끝 날짜
    },
    // slotDuration 옵션을 설정하여 한 주의 표시 간격을 조정합니다.
    slotDuration: '1:00:00',
    // 나머지 설정들은 생략

    eventAdd: function (obj) {
        console.log(obj);
    },
    eventChange: function (obj) {
        console.log(obj);
    },
    eventRemove: function (obj) {
        console.log(obj);
    },

     displayEventTime: false,
    events: totalEnergyKcalAndDateList.flatMap(function(entry) {

          return [

               {
                    title: '칼로리 : ' + entry.totalEnergyKcal + 'Kcal',
                            start: entry.date+'T01:00:00'
               },
              {
                      title: '탄수화물 : ' + entry.totalCarbohydrate + 'g',
                      start: entry.date+'T01:00:00'

              },
              {
                      title: '단백질 : ' + entry.totalProtein + 'g',
                      start: entry.date+'T02:00:00'

              },
              {
                      title: '지방 : ' + entry.totalFat + 'g',
                      start: entry.date+'T03:00:00'

              }
          ];
      }),
    dateClick: function (info) {
        selectOn = true;
        let selectedDate = info.date;
        // 선택한 날짜를 YYYY-MM-DD 형식으로 가공
        let formattedDate = formatDate(selectedDate);
        
        if (info.date > new Date()) {
            alert("오늘 이후의 날짜는 선택할 수 없습니다.");
            location.reload();
        } else {
            if (info.view.type === 'dayGridMonth') {
                if (selectedDateInfo) {
                    // 이전에 선택한 날짜가 있다면 초기화
                    selectedDateInfo.dayEl.style.backgroundColor = ''; // 원래 색상으로 돌려줌
                    selectedDateInfo.dayEl.style.borderRadius = '';
                }
                info.dayEl.style.backgroundColor = 'green'; // 'dayGridMonth'에서는 이렇게 처리
                info.dayEl.style.borderRadius = '';
                selectedDateInfo = {
                    dayEl: info.dayEl,
                    formattedDate: formattedDate
                };
            } else if (info.view.type === 'dayGridWeek') {
                if (selectedDateInfo) {
                    // 이전에 선택한 날짜가 있다면 초기화
                    selectedDateInfo.dayEl.children[0].style.backgroundColor = ''; // 원래 색상으로 돌려줌
                    selectedDateInfo.dayEl.children[0].style.borderRadius = '';
                }
                info.dayEl.children[0].style.backgroundColor = 'green'; // 'dayGridWeek'에서는 이렇게 처리
                info.dayEl.children[0].style.borderRadius = '5px';

                selectedDateInfo = {
                    dayEl: info.dayEl,
                    formattedDate: formattedDate
                };
            }

        }

        selectedDateInput.value = formattedDate;
        document.getElementById("breakfast").innerHTML="";
        document.getElementById("lunch").innerHTML="";
        document.getElementById("dinner").innerHTML="";
        document.getElementById("dessert").innerHTML="";
        spreadEatFood();
        spreadSelectDayNutrition(formattedDate);
        
    },

});
function formatDate(date) {
    let year = date.getFullYear();
    let month = String(date.getMonth() + 1).padStart(2, '0');
    let day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}
calendar.render();


/**
 * 원형프로그래스 
 * 0 = 칼로리
 *  1 = 단수화물
 *  2 = 단백질
 *  3 = 지방
 */
function progress(per ,totalEnergy,num) {
    var bar = document.getElementById('bar'+num);
    var value = document.getElementById('value'+num);
    var RADIUS = 54;
    var CIRCUMFERENCE = 2 * Math.PI * RADIUS;
    var progress = per / 100;
    var dashoffset = CIRCUMFERENCE * (1 - progress);
    if(num==0){
        value.innerHTML=totalEnergy+"Kcal";
    }else{
        value.innerHTML=totalEnergy+"g"
    }
    if(per==100){

       bar.style.stroke ="red";
    }else{
     bar.style.stroke ="#03c75a";
    }
    bar.style.strokeDashoffset = dashoffset;
    bar.style.strokeDasharray = CIRCUMFERENCE;
}





/** 선택한 날짜의 원형 프로그래스 보여주기 */
function spreadSelectDayNutrition(date){
 let kcal =0 ,Car = 0, protien =0 ,fat = 0;
    for(let i = 0 ; i<3 ; i++){
        progress(0,0,i);
      
    }

    totalEnergyKcalAndDateList.flatMap(function(entry) {
            if(entry.date ==date){
             kcal = entry.totalEnergyKcal/1964*100;
             Car = entry.totalCarbohydrate/270.2*100;
             protien = entry.totalProtein/98.3*100;
             fat = entry.totalFat/54.6*100;
            if(kcal>100){
                kcal =100;
            }
            progress(kcal,entry.totalEnergyKcal,0);
            progress(Car,entry.totalCarbohydrate,1);
            progress(protien,entry.totalProtein,2);
            progress(fat,entry.totalFat,3);
        }
   
    });
}

    

