document.querySelectorAll("button.reply").forEach(btn => {
    btn.addEventListener("click", () => {
        const reviewId = btn.dataset.reviewId;
        const responseEntry = document.querySelector('#response-input-' + reviewId);
        const responseBox = document.querySelector('#response-box-' + reviewId);
        responseBox.classList.remove('hide');
        btn.classList.add('hide');

    });
});

document.querySelectorAll("button.submit-btn").forEach(btn => {
    btn.addEventListener("click", async () => {
        const reviewId = btn.dataset.reviewId;
        const responseEntry = document.querySelector('#response-input-' + reviewId);

        await fetch(`/reviews/${reviewId}/reply`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                reply: responseEntry.value
            })
        }).then(() => {
            window.location.reload();
        });

        const replyBtns = document.querySelectorAll("button.reply");
        replyBtns.forEach(btn => {
            btn.classList.remove('hide');
        });

    });
});

document.querySelectorAll("button.remove").forEach(btn => {
    btn.addEventListener("click", async () => {
        const reviewId = btn.dataset.reviewId;
        await fetch(`/reviews/${reviewId}`, {
            method: "DELETE"
        }).then(() => {
            window.location.reload();
        });
    });
});
