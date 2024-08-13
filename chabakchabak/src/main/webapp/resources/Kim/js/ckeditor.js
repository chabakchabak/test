import {
    ClassicEditor,
    AccessibilityHelp,
    Alignment,
    AutoImage,
    Autosave,
    Bold,
    CloudServices,
    Essentials,
    FontBackgroundColor,
    FontColor,
    FontFamily,
    FontSize,
    GeneralHtmlSupport,
    Highlight,
    ImageBlock,
    ImageInsert,
    ImageInsertViaUrl,
    ImageResize,
    ImageStyle,
    ImageToolbar,
    ImageUpload,
    Italic,
    Link,
    LinkImage,
    MediaEmbed,
    Paragraph,
    SelectAll,
    SimpleUploadAdapter,
    SpecialCharacters,
    Underline,
    Undo
} from 'https://cdn.ckeditor.com/ckeditor5/42.0.2/ckeditor5.js';

import translations from 'https://cdn.ckeditor.com/ckeditor5/42.0.2/translations/ko.js';

const editorConfig = {
    toolbar: {
        items: [
            'undo',
            'redo',
            '|',
            'selectAll',
            '|',
            'fontSize',
            'fontFamily',
            'fontColor',
            'fontBackgroundColor',
            '|',
            'bold',
            'italic',
            'underline',
            '|',
            'link',
            'insertImage',
            'mediaEmbed',
            'highlight',
            '|',
            'alignment',
            '|',
            'accessibilityHelp'
        ],
        shouldNotGroupWhenFull: false
    },
    plugins: [
        AccessibilityHelp,
        Alignment,
        AutoImage,
        Autosave,
        Bold,
        CloudServices,
        Essentials,
        FontBackgroundColor,
        FontColor,
        FontFamily,
        FontSize,
        GeneralHtmlSupport,
        Highlight,
        ImageBlock,
        ImageInsert,
        ImageInsertViaUrl,
        ImageResize,
        ImageStyle,
        ImageToolbar,
        ImageUpload,
        Italic,
        Link,
        LinkImage,
        MediaEmbed,
        Paragraph,
        SelectAll,
        SimpleUploadAdapter,
        SpecialCharacters,
        Underline,
        Undo
    ],
    fontFamily: {
        supportAllValues: true
    },
    fontSize: {
        options: [10, 12, 14, 'default', 18, 20, 22],
        supportAllValues: true
    },
    htmlSupport: {
        allow: [
            {
                name: /^.*$/,
                styles: true,
                attributes: true,
                classes: true
            }
        ]
    },
    image: {
        toolbar: [
            'imageTextAlternative',
            '|',
            'imageStyle:alignBlockLeft',
            'imageStyle:block',
            'imageStyle:alignBlockRight',
            '|',
            'resizeImage'
        ],
        styles: {
            options: ['alignBlockLeft', 'block', 'alignBlockRight']
        }
    },
    language: 'ko',
    link: {
        addTargetToExternalLinks: true,
        defaultProtocol: 'https://',
        decorators: {
            toggleDownloadable: {
                mode: 'manual',
                label: 'Downloadable',
                attributes: {
                    download: 'file'
                }
            }
        }
    },
    placeholder: '글을 작성해보세요',
    translations: [translations],
    simpleUpload: {
        uploadUrl: '/Kim/upload/ckUploadFormAction',
        headers: {
            'X-CSRF-TOKEN': 'CSRF-Token',
            'Authorization': 'Bearer <JSON Web Token>'
        }
    }
};

window.onload = function() {
    ClassicEditor.create(document.querySelector('#editor'), editorConfig)
        .then(editor => {
            const form = document.querySelector('#frmRegister');
            form.addEventListener('submit', (event) => {
                const content = editor.getData();
                if (content.trim() === '') {
                    alert('내용을 입력해 주세요.');
                    event.preventDefault();
                    return;
                }
                document.querySelector('#content').value = content;

                // CKEditor에서 업로드된 이미지 정보 가져오기
                const images = Array.from(editor.model.document.getRoot().getElementsByTagName('img'));
                images.forEach((image, index) => {
                    const imageUrl = image.getAttribute('src');
                    if (imageUrl && imageUrl.startsWith('/Kim/upload/ckDisplay?fileName=')) {
                        const fileName = imageUrl.split('fileName=')[1];
                        const [uploadPath, uuidFileName] = fileName.split('/');
                        const [uuid, originalFileName] = uuidFileName.split('_');

                        const hiddenFields = `
                            <input type="hidden" name="attachList[${index}].uuid" value="${uuid}">
                            <input type="hidden" name="attachList[${index}].fileName" value="${originalFileName}">
                            <input type="hidden" name="attachList[${index}].uploadPath" value="${uploadPath}">
                        `;
                        form.insertAdjacentHTML('beforeend', hiddenFields);
                    }
                });
            });
        })
        .catch(error => {
            console.error(error);
        });
};

// 기존 드래그&드롭 파일 업로드 기능
$(function () {
    let regex = new RegExp("(.*?)\\.(exe|sh|zip|alz)$");
    let maxSize = 5242880; // 5MB

    function showImage(fileCallPath) {
        console.log("showImage...:", fileCallPath);
        $(".bigPictureWrapper").css("display", "flex").show();
        $(".bigPicture").html(`<img src="/Kim/upload/display?fileName=${fileCallPath}">`)
            .animate({
                width: "100%",
                height: "100%"
            }, 1000);

        setTimeout(function () {
            $(".bigPicture").animate({
                width: "0%",
                height: "0%"
            }, 1000);
            $(".bigPictureWrapper").fadeOut(1000);
        }, 2000);
    }

    function uploadFiles(files) {
        let formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            let fileName = files[i].name;
            let fileSize = files[i].size;
            if (!checkExtension(fileName, fileSize)) {
                return false;
            }
            formData.append("uploadFile", files[i]);
        }
        console.log(formData);

$.ajax({
    type: "post",
    url: "/Kim/upload/uploadFormAction",
    contentType: false,
    processData: false,
    data: formData,
    success: function (rData) {
        console.log(rData);
        $.each(rData, function (index, obj) {
            let imgTag;

            // todayFolderPath 변수에 담긴 값을 사용하여 경로 생성
            let todayFolderPath = `${obj.todayFolderPath}`;

            // 전체 파일 경로를 생성
            let fileName = `${todayFolderPath}/${obj.uuid}_${obj.fileName}`;

            if (!obj.image) {
                // 이미지가 아닌 파일의 경우
                imgTag = `<a href='/download?fileName=${fileName}'>
                                <img src='/resources/Kim/image/default.png' width='100'></a>`;
            } else {
                // 이미지 파일의 경우, 썸네일 경로와 원본 경로 생성
                let path = `${todayFolderPath}/s_${obj.uuid}_${obj.fileName}`; // 썸네일 경로 생성
                let callPath = `${todayFolderPath}/${obj.uuid}_${obj.fileName}`; // 원본 경로

                imgTag = `<img class="imgImage" src="/Kim/upload/display?fileName=${path}" 
                                data-callpath="${callPath}">`;
            }

            // li 태그 생성 및 추가
            let liTag = `<li
                            data-filename="${obj.fileName}" // 파일 이름만 저장
                            data-uploadpath="${obj.uploadPath}" // 경로만 저장
                            data-uuid="${obj.uuid}"
                            data-image="${obj.image}"
                            >${imgTag} <br> ${obj.fileName} <span style="cursor:pointer;"
                            data-filename="${fileName}">&times;</span></li>`; // 전체 경로를 삭제용으로 저장

            $("#uploadedList").append(liTag);
        });
    }
});

    }

    function checkExtension(fileName, fileSize) {
        if (fileSize > maxSize) {
            alert("파일 사이즈 초과");
            return false;
        }
        if (regex.test(fileName)) {
            alert("해당 파일 종류는 업로드 할 수 없습니다.");
            return false;
        }
        return true;
    }

    $("#divDrop").on("dragenter dragover", function (e) {
        e.preventDefault();
    });

    $("#divDrop").on("drop", function (e) {
        e.preventDefault();
        console.log(e.originalEvent.dataTransfer.files);
        let files = e.originalEvent.dataTransfer.files;
        uploadFiles(files);
    });

    $("#divDrop").on("click", function () {
        $("#fileInput").click();
    });

    $("#fileInput").on("change", function (e) {
        let files = e.target.files;
        uploadFiles(files);
    });

    $("#uploadedList").on("click", ".imgImage", function () {
        let callPath = $(this).data("callpath");
        showImage(callPath);
    });

    $("#uploadedList").on("click", "li > span", function () {
        let that = $(this);
        let filename = that.data("filename");
        console.log("filename:", filename); // 추가된 부분: filename 값 확인용 로그
        let sData = { fileName: filename };
        console.log("sData:", sData);
        $.ajax({
            type: "delete",
            url: "/Kim/upload/delete",
            data: JSON.stringify(sData), // JSON 형태로 데이터 전송
            contentType: "application/json; charset=utf-8", // contentType 설정
            success: function (rData) {
                console.log(rData);
                that.parent().remove(); // 성공 시 목록에서 항목 제거
            },
            error: function (error) {
                console.error("Error deleting file:", error);
            }
        });
    });

    $("#frmRegister").submit(function () {
        $("#uploadedList > li").each(function (i) {
            let fileName = $(this).data("filename");
            let uploadPath = $(this).data("uploadpath");
            let uuid = $(this).data("uuid");
            let image = $(this).data("image");
            let inputTag = `<input type="hidden" name="attachList[${i}].fileName" value="${fileName}">
                                <input type="hidden" name="attachList[${i}].uploadPath" value="${uploadPath}">
                                <input type="hidden" name="attachList[${i}].uuid" value="${uuid}">
                                <input type="hidden" name="attachList[${i}].image" value="${image ? 'I' : 'F'}">`;
            $("#frmRegister").prepend(inputTag);
        });
    });
});
