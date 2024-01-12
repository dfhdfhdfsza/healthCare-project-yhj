let alarmStatus = 0;
document.querySelectorAll('.nav_box_ul li:not(#alarm-li) i').forEach(function(icon) {
    icon.addEventListener('click', function() {
        var anchorElement = icon.nextElementSibling;
        anchorElement.click();
    });
});

document.getElementById('alarm-li').addEventListener('click', function() {
    var elementsToToggle = document.querySelectorAll('.nav_box_ul li:not(#alarm-li) a, #alarm-li a');
     var logo = document.querySelector('.nav_logo');
     var navbox = document.querySelector('.nav_box');
     var alarmbox = document.getElementById('alarm-box');
     var alarmContent =document.getElementById('alarm-content');
    elementsToToggle.forEach(function(element) {
        if (alarmStatus === 0) {
            element.style.display = 'none';
            logo.style.color = 'transparent';
            navbox.style.width = '70px';
             alarmbox.style.left ='70px';
             setTimeout(function() {
                alarmContent.style.display = '';
              }, 300);
        } else {
            element.style.display = '';
            logo.style.color = 'white';
            navbox.style.width = '250px';
            alarmbox.style.left = '-300px';
            alarmContent.style.display='none';
        }
    });

    alarmStatus = (alarmStatus === 0) ? 1 : 0;
});