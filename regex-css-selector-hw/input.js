regExps = {
"exercise_1": /Internet|Words|Or/,
"exercise_2": /088[1-7]{7}/,
"exercise_3": /\D+|[^10]/,
"exercise_4": /^val.{9,11}|^i../,
"exercise_5": /^[^2]\d{1,2}[^1]$/,
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
