regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[1-9]{7}/,
"exercise_3": /[^10]+/,
"exercise_4": /^[^.0-9_][A-Za-z0-9._]{2,30}$/,
"exercise_5": /^([1-9][0-9]{2}|1[0-4][0-9]{2}|15[0]{2})$/,
"exercise_6": /class.+["']/
};
cssSelectors = {
"exercise_1": "item > java.class1",
"exercise_2": "different > java",
"exercise_3": "java > tag.class1.class2",
"exercise_4": "css > item + item + item",
"exercise_5": "item > tag.class1.class2 > java + java.class1",
"exercise_6": "item#someId > item > item > item > item",
"exercise_7": "different#diffId2 > java + java",
"exercise_8": "item#someId"
};