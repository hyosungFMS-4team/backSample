// 마커를 담을 배열입니다
let markers = [];

let mapContainer = document.getElementById('map'), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };

// 지도를 생성합니다
let map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
let ps = new kakao.maps.services.Places();
let overlayList = [];
// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다

// 키워드로 장소를 검색합니다
searchPlaces();
document.getElementById('search_form').onsubmit = () => searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
  let keyword = document.getElementById('keyword').value;

  if (!keyword.replace(/^\s+|\s+$/g, '')) {
    alert('키워드를 입력해주세요!');
    return false;
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB,{
    size:7,
  });

  return false;
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);
    displaySearchResult(data);
    // 페이지 번호를 표출합니다
    displayPagination(pagination);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert('검색 결과가 존재하지 않습니다.');
    return;
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert('검색 결과 중 오류가 발생했습니다.');
    return;
  }
}

function displaySearchResult(data) {
  const searchResult = document.querySelector('.search_result');
  let keyword = document.getElementById('keyword').value;
  searchResult.textContent = `"${keyword}"의 검색결과 (${data.length})`;
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
async function displayPlaces(places) {
  let listEl = document.getElementById('placesList'),
    menuEl = document.getElementById('search_wrap'),
    fragment = document.createDocumentFragment(),
    bounds = new kakao.maps.LatLngBounds(),
    listStr = '';
    console.log(places)
  // 검색 결과 목록에 추가된 항목들을 제거합니다
  removeAllChildNods(listEl);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeMarker();

  if(overlayList[0]){
    overlayList = [];
  }
  for (let i = 0; i < places.length; i++) {
    // 마커를 생성하고 지도에 표시합니다
    let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
      marker = addMarker(placePosition, i),
      itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    let customOverlay = new kakao.maps.CustomOverlay({
      position: placePosition,
      content: `<div class="customoverlay">
                            <div class="thumbnail"><img src=""></div>
                              <div class="middle">
                                  <div class="category">${places[i].category_name}</div>
                                  <div class="place_name" style="font-size: ${places[i].place_name.length>10?"14px":"18px"}">${places[i].place_name}</div>
                                  <div class="reviews">
                                    <span class="ratings"></span>
                                    <span class="starswrap"><span class="star_ratings" id="starRating"><span></span></span></span>
                                    <span class="reviewCnt"></span>
                                  </div>
                                  <div class="address">${places[i].address_name}</div>
                                  <div class="phone">${places[i].phone}</div>
                              </div>
                              <div class="right">
                                <img src="../images/heart.svg" alt="">
                              </div>
                            </div>`,

      yAnchor: 1.34, // 오버레이의 y축 기준점을 설정합니다 (0: 위쪽, 1: 아래쪽)
      xAnchor: 0.5 // 오버레이의 x축 기준점을 설정합니다 (0: 왼쪽, 1: 오른쪽)
    });
    overlayList.push(customOverlay);
    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function (marker, title) {
      kakao.maps.event.addListener(marker, 'mouseover', function () {
        makeOverListener(map,customOverlay);

      });

      kakao.maps.event.addListener(marker, 'mouseout', function () {
        makeOutListener(customOverlay);

      });

      itemEl.addEventListener("mouseover",(e)=> {
        // Check if the event target is the element itself
          //custeroverlay open logic
          map.setCenter(placePosition);
          makeOverListener(map,customOverlay);
      })

      itemEl.addEventListener("mouseout",(e)=>{// Check if the event target is the element itself
        if (!itemEl.contains(e.relatedTarget)) {
          // infowindow close logic
          makeOutListener(customOverlay);
        }
      })
    })(marker, places[i].place_name);

    fragment.appendChild(itemEl);
  }
  getImages(places);

  // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
  listEl.appendChild(fragment);
  menuEl.scrollTop = 0;

  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
  map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
  let el = document.createElement('li'),
    itemStr = `<span class="markerbg marker_${index + 1}"></span><div class="info"><h5><a href="${places.place_url}">${places.place_name}</a></h5>`;
  if (places.road_address_name) {
    itemStr += `<span>${places.road_address_name}</span><span class="jibun gray">${places.address_name}</span>`;
  } else {
    itemStr +=`<span>${places.address_name}</span>`;
  }
  itemStr +=`<img src="images/heart.svg" class="like" id="${index+1}">`;

  el.innerHTML = itemStr;
  el.className = 'item';

  // TODO:
  return el;
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map, overlay) {
  overlay.setMap(map);
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(overlay) {
  overlay.setMap(null);
}

function createTravelplanItem(places) {
  console.log(places);
  fetchHTML(places.place_name).then(res => {
    console.log(res);
    extractInfoFromHTML(res);
  });
}

async function fetchHTML(place_name) {
  try {
    const url = `https://www.google.com/search?q=${place_name}`;

    const response = await fetch(url);
    console.log(response);

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const html = await response.text();
    return html;
  } catch (error) {
    console.error('Error fetching HTML:', error);
    return null;
  }
}

// HTML에서 원하는 정보를 추출하는 함수
function extractInfoFromHTML(html) {
  const parser = new DOMParser();
  const doc = parser.parseFromString(html, 'text/html');

  console.log(doc);
  const imgElement = doc.getElementById('dimg_61');
  console.log(imgElement);

  // const imageElement = doc.querySelector('.bg_present');
  // doc.querySelector();
  // console.log(imageElement);
  // const title = titleElement ? titleElement.textContent.trim() : 'Title not found';

  // return title;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
  let imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
    imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
    imgOptions = {
      spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
      spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
      offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    },
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
    marker = new kakao.maps.Marker({
      position: position, // 마커의 위치
      image: markerImage,
    });

  marker.setMap(map); // 지도 위에 마커를 표출합니다
  markers.push(marker); // 배열에 생성된 마커를 추가합니다

  return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  for (let i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
  let paginationEl = document.getElementById('pagination'),
    fragment = document.createDocumentFragment(),
    i;

  // 기존에 추가된 페이지번호를 삭제합니다
  while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild(paginationEl.lastChild);
  }

  for (i = 1; i <= pagination.last; i++) {
    let el = document.createElement('a');
    el.href = '#';
    el.innerHTML = i;

    if (i === pagination.current) {
      el.className = 'on';
    } else {
      el.onclick = (function (i) {
        return function () {
          pagination.gotoPage(i);
        };
      })(i);
    }

    fragment.appendChild(el);
  }
  paginationEl.appendChild(fragment);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
  while (el.hasChildNodes()) {
    el.removeChild(el.lastChild);
  }
}

async function getImages(places) {
  let placeUrls = [];
    for (const x of places) {
      placeUrls.push({placeUrl : x.place_url});
    }
  const response = await fetch('/crawl', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(placeUrls),
  }).then(response=>{
    if (response.ok) {
      return response.text();
    } else {
      throw new Error('Network response was not ok.');
    }
  }).then(res=>{
    const thumbnail = document.querySelectorAll(".thumbnail")
    const ratings = document.querySelectorAll(".ratings")
    const crawledResults = JSON.parse(res);
    console.log(crawledResults)
    //크롤링 데이터 다 받아온 후 각 커스텀오버레이들에 별점과 썸네일 이미지 추가
    console.log(overlayList)
    let starWidth;

    overlayList.forEach((x,idx)=>{
      switch (true){
        case crawledResults[idx][1] < 0.5 && crawledResults[idx][1] >= 0 :
          starWidth = "0%";
          break
        case crawledResults[idx][1] <= 1 :
          starWidth = "9%";
          break
        case crawledResults[idx][1] < 1.5 :
          starWidth = "21%";
          break
        case crawledResults[idx][1] <= 2 :
          starWidth = "29%";
          break
        case crawledResults[idx][1] < 2.5 :
          starWidth = "41%";
          break
        case crawledResults[idx][1] <= 3 :
          starWidth = "49%";
          break
        case crawledResults[idx][1] < 3.5 :
          starWidth = "61%";
          break
        case crawledResults[idx][1] <= 4 :
          starWidth = "70%";
          break
        case crawledResults[idx][1] < 4.5 :
          starWidth = "81%";
          break
        case crawledResults[idx][1] <= 5 :
          starWidth = "90%";
          break
      }
      let content = `<div class="customoverlay">
                            <div class="thumbnail"><img src="${crawledResults[idx][0]}"></div>
                              <div class="middle">
                                  <div class="category">${places[idx].category_name}</div>
                                  <div class="place_name" style="font-size: ${places[idx].place_name.length>10?"14px":"18px"}">${places[idx].place_name}</div>
                                  <div class="reviews">
                                    <span class="ratings">${crawledResults[idx][1]}</span>
                                    <span class="starswrap"><span class="star_ratings" id="starRating"><span style="width: ${starWidth}"></span></span></span>
                                    <span class="reviewCnt">${crawledResults[idx][2].replaceAll("(","").replaceAll(")","")}건</span>
                                  </div>
                                  <div class="address">${places[idx].address_name}</div>
                                  <div class="phone">${places[idx].phone}</div>
                              </div>
                              <div class="right">
                                <img src="../images/heart.svg" alt="">
                              </div>
                            </div>`
      x.setContent(content)

    })

  }).catch(error=>{
    console.error('There was a problem with the fetch operation:', error);
  })
}
