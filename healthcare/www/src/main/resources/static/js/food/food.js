let totalCarbohydrate = 0;
let totalProtein = 0;
let totalFat = 0;


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
        if(keyword != ""){
            for(let i =0 ; i < result.length; i++){
                console.log(result[i]);
                ResultHTML +=  `<div>${result[i].processedFoodItemName}<button popovertarget='food${i}'>+</button></div>`;
                ResultHTML += `<div id='food${i}' class='food-popover'  popover> ${result[i].processedFoodItemName}`; 
                ResultHTML += `<form th:action="@{/food/checkFood}"  method="post">`;
                ResultHTML +=  ` 칼로리 :  ${result[i].energyKcal}`;
                ResultHTML +=  `탄 :  ${result[i].carbohydrate}`;
                ResultHTML +=  `단 : ${result[i].protein} `;
                ResultHTML +=  `지 : ${result[i].fat} <br>`;
//                ResultHTML += `  <input type='radio' name='time' value='breaktime' />아침`;
//                ResultHTML += `  <input type='radio' name='time' value='lunch' />점심`;
//                ResultHTML += `  <input type='radio' name='time' value='dinner' />저녁`;
                ResultHTML += `  <button type='submit'>+</button></form> </div>`;
            }
            searchResult.innerHTML += ResultHTML;
        }
        
    } catch (error) {
        console.error(error);
    }
}

//function addNutrition(foodName, carbohydrate, protein, fat) {
//     totalCarbohydrate += carbohydrate;
//     totalProtein +=protein;
//     totalFat +=fat;
//     document.getElementById('keywordInput').value ="";
//     console.log("오늘먹은탄수화물"+totalCarbohydrate);
//     console.log("오늘먹은단백질"+totalProtein);
//     console.log("오늘먹은지방"+totalFat);
//}

