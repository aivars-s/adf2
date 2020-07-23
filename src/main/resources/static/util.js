function initCustomerSearch(selector) {
  $(selector).select2({
    ajax: {
      url: "/customers/search",
      dataType: "json",
      data: function (params) {
        return {query: params.term}
      },
      processResults: function (data) {
        return {results: data}
      },
      delay: 200,
    },
    theme: "bootstrap4",
    minimumInputLength: 2,
    placeholder: "Type your search query"
  })
}

function initProductSearch(selector) {
  $(selector).select2({
    ajax: {
      url: "/products/search",
      dataType: "json",
      data: function(params) {
        return { query: params.term }
      },
      processResults: function(data) {
        return { results: data }
      },
      delay: 200,
    },
    theme: "bootstrap4",
    minimumInputLength: 2,
    placeholder: "Type your search query"
  })
}

function addProductSelect() {
  var productId = "product-" + nextId
  var productSelectId = "product-select-"
  $("<div class=\"form-group\" id=\"" + productId + "\">\n" +
  "    <div class=\"input-group\">\n" +
  "        <div class=\"input-group-prepend\">\n" +
  "            <span class=\"input-group-text\">Product and amount</span>\n" +
  "        </div>\n" +
  "        <select id=\"" + productSelectId + "\" class=\"form-control\" name=\"product\" required></select>\n" +
  "        <input type=\"number\" name=\"amount\" step=\"1\" min=\"1\" value=\"1\" required>\n" +
  "        <div class=\"input-group-append\">\n" +
  "            <button class='btn btn-outline-danger' type=\"button\" onclick=\"removeProductSelect('" + productId + "')\">Delete</button>" +
  "        </div>" +
  "    </div>\n" +
  "</div>").insertBefore($("#buttonStart"))
  initProductSearch(productSelectId)
  nextId++
}

function removeProductSelect(productId) {
  $("#" + productId).remove()
}