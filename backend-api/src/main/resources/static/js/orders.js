document.querySelectorAll("button.accept").forEach(btn => {
    btn.addEventListener("click", async () => {
        const orderId = btn.dataset.orderId;
        await fetch(`/api/orders/${orderId}/accept`, {
            method: "PUT"
        }).then(() => {
            window.location.reload();
        });
    });
});

document.querySelectorAll("button.reject").forEach(btn => {
    btn.addEventListener("click", async () => {
        const orderId = btn.dataset.orderId;
        await fetch(`/api/orders/${orderId}/reject`, {
            method: "PUT"
        }).then(() => {
            window.location.reload();
        });
    });
});
