export function registerServiceWorker() {
    navigator.serviceWorker
    .register("firebase-messaging-sw.js")
    .then(function (registration) {
        console.log("Service Worker 등록 성공:", registration)
    })
    .catch(err => console.log("Service Worker 등록 실패:", err))
}

export function requestPermission() {
    console.log("승인 요청 중")
    Notification.requestPermission().then((permission) => {
        if (permission === 'granted') {
            console.log("승인 완료")
        }
    })
}