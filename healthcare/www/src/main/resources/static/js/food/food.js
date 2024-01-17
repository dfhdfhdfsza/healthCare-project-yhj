async function findFood() {
    var keyword = document.getElementById("keywordInput").value;
    let searchResult = document.getElementById('serchresult');

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
            searchResult.innerHTML +=  "<div>"+ result[i].processedFoodItemName+"</div>";
        }
        }
        
    } catch (error) {
        console.error(error);
    }
}
