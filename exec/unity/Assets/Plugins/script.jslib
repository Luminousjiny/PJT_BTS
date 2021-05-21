mergeInto(LibraryManager.library, {

  Hello: function () {
    this.text = 123;
    window.alert("hello");
  },

  HelloString: function (str) {
    window.alert(Pointer_stringify(str));
  },

  UnityfuncHook: function (str){
    _funcJShook(Pointer_stringify(str));
  },
  UnityroomHook: function (str){
    _roomJShook(Pointer_stringify(str));
  },
  UnityuserHook: function (str){
    _userJShook(Pointer_stringify(str));
  },
  unityStartEnd : function (str){
 
   },
  PrintFloatArray: function (array, size) {
    for(var i = 0; i < size; i++)
    console.log(HEAPF32[(array >> 2) + i]);
  },

  AddNumbers: function (x, y) {
    return x + y;
  },

  StringReturnValueFunction: function () {
    var returnStr = "plz this text recived";
    var bufferSize = lengthBytesUTF8(returnStr) + 1;
    var buffer = _malloc(bufferSize);
    stringToUTF8(returnStr, buffer, bufferSize);
    return buffer;
  },

  BindWebGLTexture: function (texture) {
    GLctx.bindTexture(GLctx.TEXTURE_2D, GL.textures[texture]);
  },

});