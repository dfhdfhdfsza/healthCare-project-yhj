let alarmStatus = 0;
let showHeart = false;
document.querySelectorAll('.nav_box_ul li:not(#alarm-li) i').forEach(function(icon) {
    icon.addEventListener('click', function() {
        var anchorElement = icon.nextElementSibling;
        anchorElement.click();
    });
});

//알람 li 눌렀을때 발생하는 이벤트
document.getElementById('alarm-li').addEventListener('click', function() {
    var elementsToToggle = document.querySelectorAll('.nav_box_ul li:not(#alarm-li) a, #alarm-li a');
     var navbox = document.querySelector('.nav_box');
     var alarmbox = document.getElementById('alarm-box');
     var alarmContent =document.getElementById('alarm-content');

    elementsToToggle.forEach(function(element) {
        if (alarmStatus === 0) {
            element.style.display = 'none';
            navbox.style.width = '70px';
             alarmbox.style.left ='70px';
             setTimeout(function() {
                alarmContent.style.display = '';
              }, 300);
        } else {
            element.style.display = '';
            navbox.style.width = '250px';
            alarmbox.style.left = '-300px';
            alarmContent.style.display='none';
        }
    });

    alarmStatus = (alarmStatus === 0) ? 1 : 0;
    toggleHeartIcon();
    console.log(showHeart);
});

/** 하트버튼 선택 애니메이션  */
 function toggleHeartIcon() {
            var heartIcon = document.getElementById("heartIcon");
            var houseIcon =document.getElementById("houseIcon");
             var alarmLi = document.querySelector('#alarm-li')
            if (showHeart) {
                heartIcon.classList.remove("bi-heart-fill");
                heartIcon.classList.add("bi-heart");
                alarmLi.style.border = "none";
                
                houseIcon.classList.remove("bi-house");
                houseIcon.classList.add("bi-house-fill");
               
            } else {
                heartIcon.classList.remove("bi-heart");
                heartIcon.classList.add("bi-heart-fill");
                alarmLi.style.border = "solid 1px #aaa";

                houseIcon.classList.remove("bi-house-fill");
                houseIcon.classList.add("bi-house");
            }


            showHeart = !showHeart;
        }





async function selectProfileImage(){
    try {
        const resp = await fetch("/user/selectProfileImage");
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function viewImage(){
    selectProfileImage().then(result=>{
        if(result != null){
            let image = document.querySelector('.item-main-img-nav');
            let filePath = `/userFile/${result.userFileSaveDir}/${result.userUUID + "_" + result.userFileName}`;
            image.src = filePath;
            console.log(result);
        }
    })
}