
function Validator(options) {
    let formElement = document.querySelector(options.form);

    //Validate các thành phần form
    function validate(inputElement, rule) {
        let errorElement = inputElement.parentElement.querySelector(options.errorSelector);
        let errorMessage = rule.test(inputElement.value);
        if (errorMessage) {
            errorElement.innerHTML = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        }else {
            errorElement.innerHTML = "";
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    // Lấy Element của form
    if (formElement){
       options.rules.forEach( function (rule) {
           let inputElement = formElement.querySelector(rule.selector);
           if (inputElement) {
               // Xử lý mỗi khi người dùng blur ra khỏi input
               inputElement.onblur = function () {
                   // value: inputElement.value
                   // test func : rule.test
                  validate(inputElement,rule)
               }

               // Xử lý mỗi khi người dùng nhập vào input
               inputElement.oninput = function () {
                   let errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                   errorElement.innerHTML = "";
                   inputElement.parentElement.classList.remove('invalid');
               }
           }
       });
    }
}

Validator.isRequired = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            return value.trim() ? undefined : "Vui lòng nhập trường này!";
        }
    };
}
Validator.isEmail = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : "Bạn chưa nhập email, Vd: NguyenVanA@gmail.com"
        }
    };
}

Validator.minLength = function (selector, min) {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min ? undefined : `Vui lòng nhập tối thiểu ${min} ký tự`;
        }
    };
}
