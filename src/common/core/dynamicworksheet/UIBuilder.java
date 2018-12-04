package core.dynamicworksheet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.jsondummy.IJsonDummy;
import core.dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import core.dynamicworksheet.jsondummy.value.IJsonDummyValue;
import core.dynamicworksheet.util.ElementGsonAdapter;
import core.dynamicworksheet.util.ValidationCaseGsonAdapter;
import core.dynamicworksheet.util.ValueGsonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для парсинга джейсона в список ядерных элементов (как правило, в списке один элемент - визард)
 * Всё взаимодействие между элементами в рамках одного джейсона происходит под капотом, при реализации
 * UI на конкретной платформе реализовывать эту логику не нужно. Подробнее см. в комментариях для
 * {@link IElement}
 */
public class UIBuilder {

    private static final String JSON = "[{\"id\":\"153d3e67-730a-494f-b8c0-e954979bae21\",\"ui\":\"wizard\",\"c\":[{\"id\":\"88e62e28-5dcf-495f-9206-fb7c2c2571d7\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"defc2c86-67ea-4484-ac29-6905725cf042\",\"ui\":\"text\",\"kind\":\"title\",\"value\":{\"type\":\"const\",\"value\":\"Персональные данные\"}},{\"id\":\"6c310b47-39a7-4bd3-a632-d12e6853cead\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"hywq7d30f\",\"bindPath\":\"gender\",\"ui\":\"radio\",\"kind\":\"button\",\"subType\":\"radio\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Пол\",\"vn\":\"Пол\",\"en\":\"Пол\"}},\"validations\":[],\"options\":[{\"label\":\"Мужской\",\"value\":\"man\"},{\"label\":\"Женский\",\"value\":\"woman\"}]}]},{\"id\":\"a8dc1910-8209-40a9-b608-50ba7b9a0301\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"zmjafia1m\",\"bindPath\":\"lastName\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"maxLength\":30,\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фамилия\",\"vn\":\"Фамилия\",\"en\":\"Last Name\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фамилия\",\"vn\":\"Фамилия\",\"en\":\"Фамилия\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"23zlrkmnz\",\"bindPath\":\"firstName\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Имя\",\"vn\":\"Имя\",\"en\":\"Имя\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Имя\",\"vn\":\"Имя\",\"en\":\"Имя\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"ujd9tr5ys\",\"bindPath\":\"surName\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Отчество\",\"vn\":\"Отчество\",\"en\":\"Отчество\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Отчество\",\"vn\":\"Отчество\",\"en\":\"Отчество\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]},{\"id\":\"68331ed0-a5dd-4741-ad68-8b7a0b5b8b3a\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"dgdlqvre4\",\"bindPath\":\"phone\",\"ui\":\"input\",\"subType\":\"phone\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"+7 (999) 999-99-99\",\"prefix\":\"\",\"suffix\":\"\",\"verify\":true,\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Мобильный телефон\",\"vn\":\"Мобильный телефон\",\"en\":\"Мобильный телефон\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Мобильный телефон\",\"vn\":\"Мобильный телефон\",\"en\":\"Мобильный телефон\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите номер телефона\",\"vn\":\"укажите номер телефона\",\"en\":\"укажите номер телефона\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]},{\"id\":\"2f7f1450-6631-45c5-a6fa-9c5ca5f6ce7f\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"j99kkdwy7\",\"bindPath\":\"mail\",\"ui\":\"input\",\"subType\":\"email\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"maxLength\":200,\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Почта\",\"vn\":\"Почта\",\"en\":\"Почта\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Почта\",\"vn\":\"Почта\",\"en\":\"Почта\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите почту, например mail@mail.ru\",\"vn\":\"укажите почту, например mail@mail.ru\",\"en\":\"укажите почту, например mail@mail.ru\"}},\"validations\":[{\"valid\":{\"type\":\"minLength\",\"minLength\":10},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"почта не может быть меньше 10 символов\",\"vn\":\"почта не может быть меньше 10 символов\",\"en\":\"почта не может быть меньше 10 символов\"}}},{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]}]},{\"id\":\"0236848a-d43c-4058-b103-c5b29079e7b0\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"26db6fdd-dd7b-4508-aa1d-520b2a6150ec\",\"ui\":\"text\",\"kind\":\"title\",\"value\":{\"type\":\"const\",\"value\":\"Паспорт\"}},{\"id\":\"fa6e702e-cbe9-44cc-9635-3e95cfe03e9c\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"v34yrjrlw\",\"bindPath\":\"serial\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"9999 999999\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Серия и номер паспорта\",\"vn\":\"Серия и номер паспорта\",\"en\":\"Серия и номер паспорта\"}},\"title\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Укажите быстро\",\"vn\":\"Укажите быстро\",\"en\":\"Укажите быстро\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"серия и номер паспорта\",\"vn\":\"серия и номер паспорта\",\"en\":\"серия и номер паспорта\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"hjnzcx22q\",\"bindPath\":\"birthDate\",\"ui\":\"date\",\"subType\":\"date\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"DD.MM.YYYY\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дата рождения\",\"vn\":\"Дата рождения\",\"en\":\"Дата рождения\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дата рождения\",\"vn\":\"Дата рождения\",\"en\":\"Дата рождения\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}],\"dateFormat\":\"DD.MM.YYYY\"},{\"id\":\"ijckhzdyx\",\"bindPath\":\"birthPlace\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Место рождения\",\"vn\":\"Место рождения\",\"en\":\"Место рождения\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Место рождения\",\"vn\":\"Место рождения\",\"en\":\"Место рождения\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]},{\"id\":\"720c477f-54be-4b46-ab1a-1b1b6e29e8a8\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"3d5thkbyx\",\"bindPath\":\"code\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"999-999\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Код подразделения\",\"vn\":\"Код подразделения\",\"en\":\"Код подразделения\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Код подразделения\",\"vn\":\"Код подразделения\",\"en\":\"Код подразделения\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"0wvdxpmmm\",\"bindPath\":\"documentDate\",\"ui\":\"date\",\"subType\":\"date\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"DD.MM.YYYY\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дата выдачи паспорта\",\"vn\":\"Дата выдачи паспорта\",\"en\":\"Дата выдачи паспорта\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дата выдачи паспорта\",\"vn\":\"Дата выдачи паспорта\",\"en\":\"Дата выдачи паспорта\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}],\"dateFormat\":\"DD.MM.YYYY\"},{\"id\":\"g9psnhyuo\",\"bindPath\":\"documentPlace\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Место выдачи паспорта\",\"vn\":\"Место выдачи паспорта\",\"en\":\"Место выдачи паспорта\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Место выдачи паспорта\",\"vn\":\"Место выдачи паспорта\",\"en\":\"Место выдачи паспорта\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]},{\"id\":\"04a0eb43-174d-4138-b572-734887cd105f\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"56hlh3klw\",\"bindPath\":\"filePhoto\",\"ui\":\"file\",\"subType\":\"file\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фото документа\",\"vn\":\"Фото документа\",\"en\":\"Фото документа\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фото\",\"vn\":\"Фото\",\"en\":\"Фото\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"загрузите файл\",\"vn\":\"загрузите файл\",\"en\":\"загрузите файл\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}},{\"valid\":{\"type\":\"uploadValid\",\"maxFileSize\":\"10mb\",\"extensions\":\"png, jpg\",\"minWidth\":100,\"maxWidth\":1000,\"minHeight\":100,\"maxHeight\":1000},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"доступные расширения png, jpg, 10mb\",\"vn\":\"доступные расширения png, jpg, 10mb\",\"en\":\"доступные расширения png, jpg, 10mb\"}}}]}]}]},{\"id\":\"626dca32-2396-4ada-a937-e63585ecc29c\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"b1b8f636-5b59-4c93-b916-0c3f36a22b1c\",\"ui\":\"text\",\"kind\":\"title\",\"value\":{\"type\":\"const\",\"value\":\"Адрес постоянной регистрации\"}},{\"id\":\"c72620c4-89fe-4c11-97f8-1f444981b407\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"7nx85ok4b\",\"bindPath\":\"country\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Страна\",\"vn\":\"Страна\",\"en\":\"Страна\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Страна\",\"vn\":\"Страна\",\"en\":\"Страна\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"5ty8q9co0\",\"bindPath\":\"city\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Город\",\"vn\":\"Город\",\"en\":\"Город\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Город\",\"vn\":\"Город\",\"en\":\"Город\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]},{\"id\":\"57d2a7c2-fc1c-4e22-8243-1aaa4cc2297b\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"rzsnbjhg0\",\"bindPath\":\"street\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Улица\",\"vn\":\"Улица\",\"en\":\"Улица\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Улица\",\"vn\":\"Улица\",\"en\":\"Улица\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"cfjoymiwk\",\"bindPath\":\"home\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дом\",\"vn\":\"Дом\",\"en\":\"Дом\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дом\",\"vn\":\"Дом\",\"en\":\"Дом\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[]}]}]},{\"id\":\"75d9a0b3-1833-47ad-b82d-384ef0079e25\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"2c7be931-200c-4db2-8240-0d933cd99e49\",\"ui\":\"text\",\"kind\":\"title\",\"value\":{\"type\":\"const\",\"value\":\"Адрес фактической регистрации\"}},{\"id\":\"dee6e4d1-70b7-42de-9038-2facf640ce24\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"n79oqxswd\",\"bindPath\":\"registration\",\"ui\":\"check\",\"subType\":\"checkbox\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Совпадает с адресом фактического проживания\",\"vn\":\"Совпадает с адресом фактического проживания\",\"en\":\"Совпадает с адресом фактического проживания\"}},\"validations\":[]}]},{\"id\":\"190404ea-890f-4b89-b226-e0253e069275\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"ircbblxkg\",\"bindPath\":\"country\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"hidden\":{\"type\":\"and\",\"values\":[{\"type\":\"and\",\"values\":[{\"type\":\"eq\",\"value\":{\"type\":\"value\",\"ref\":\"n79oqxswd\"},\"other\":{\"type\":\"const\",\"value\":\"true\"}}]}]},\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Страна\",\"vn\":\"Страна\",\"en\":\"Страна\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Страна\",\"vn\":\"Страна\",\"en\":\"Страна\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"jqajitjuc\",\"bindPath\":\"city\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"hidden\":{\"type\":\"and\",\"values\":[{\"type\":\"and\",\"values\":[{\"type\":\"eq\",\"value\":{\"type\":\"value\",\"ref\":\"n79oqxswd\"},\"other\":{\"type\":\"const\",\"value\":\"true\"}}]}]},\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Город\",\"vn\":\"Город\",\"en\":\"Город\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Город\",\"vn\":\"Город\",\"en\":\"Город\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]}]},{\"id\":\"a552db82-d51b-4f2f-a724-3e4e14cb6aaf\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"x1tkf1iah\",\"bindPath\":\"street\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"hidden\":{\"type\":\"and\",\"values\":[{\"type\":\"and\",\"values\":[{\"type\":\"eq\",\"value\":{\"type\":\"value\",\"ref\":\"n79oqxswd\"},\"other\":{\"type\":\"const\",\"value\":\"true\"}}]}]},\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Улица\",\"vn\":\"Улица\",\"en\":\"Улица\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Улица\",\"vn\":\"Улица\",\"en\":\"Улица\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}}]},{\"id\":\"ek271grek\",\"bindPath\":\"home\",\"ui\":\"input\",\"subType\":\"text\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"hidden\":{\"type\":\"and\",\"values\":[{\"type\":\"and\",\"values\":[{\"type\":\"eq\",\"value\":{\"type\":\"value\",\"ref\":\"n79oqxswd\"},\"other\":{\"type\":\"const\",\"value\":\"true\"}}]}]},\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дом\",\"vn\":\"Дом\",\"en\":\"Дом\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Дом\",\"vn\":\"Дом\",\"en\":\"Дом\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"укажите точно как в паспорте\",\"vn\":\"укажите точно как в паспорте\",\"en\":\"укажите точно как в паспорте\"}},\"validations\":[]}]}]},{\"id\":\"30374115-6b02-4887-be6d-121bb6bf7b5d\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"5af754b8-cbae-444d-8cf7-be8cec84d674\",\"ui\":\"text\",\"kind\":\"title\",\"value\":{\"type\":\"const\",\"value\":\"Социальный статус\"}},{\"id\":\"eaa4baab-ace8-41d5-9e1a-cea97d4ee387\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"pw2blu8hc\",\"bindPath\":\"family\",\"ui\":\"select\",\"subType\":\"dropdown\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Семейное положение\",\"vn\":\"Семейное положение\",\"en\":\"Семейное положение\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Семейное положение\",\"vn\":\"Семейное положение\",\"en\":\"Семейное положение\"}},\"validations\":[{\"valid\":{\"type\":\"required\"}}],\"options\":[{\"label\":\"В браке\",\"value\":\"0\"},{\"label\":\"Не замужем / не женат\",\"value\":\"1\"}]}]},{\"id\":\"c772d027-f905-46f9-a9be-101473dcf161\",\"ui\":\"container\",\"layout\":\"horizontal\",\"c\":[{\"id\":\"6mlei03b2\",\"bindPath\":\"isWork\",\"ui\":\"select\",\"subType\":\"dropdown\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Выберите тип занятости\",\"vn\":\"Выберите тип занятости\",\"en\":\"Выберите тип занятости\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Выберите тип занятости\",\"vn\":\"Выберите тип занятости\",\"en\":\"Выберите тип занятости\"}},\"validations\":[{\"valid\":{\"type\":\"required\"}}],\"options\":[{\"label\":\"Работаю\",\"value\":\"0\"},{\"label\":\"Не работаю\",\"value\":\"1\"},{\"label\":\"Студент\",\"value\":\"3\"}]}]}]},{\"id\":\"d5de441e-65de-4e44-bcab-8d43ebfd55ab\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"535446d7-4d41-434a-9d66-5b3ad23254b2\",\"ui\":\"text\",\"kind\":\"title\",\"value\":{\"type\":\"const\",\"value\":\"Документы\"}},{\"id\":\"2e27e7ad-ce37-4146-b72f-3ca28f05d74c\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"9ydwhdesi\",\"bindPath\":\"filePhoto\",\"ui\":\"file\",\"subType\":\"file\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фото документа\",\"vn\":\"Фото документа\",\"en\":\"Фото документа\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фото\",\"vn\":\"Фото\",\"en\":\"Фото\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"загрузите файл\",\"vn\":\"загрузите файл\",\"en\":\"загрузите файл\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}},{\"valid\":{\"type\":\"uploadValid\",\"maxFileSize\":\"10mb\",\"extensions\":\"png, jpg\",\"minWidth\":100,\"maxWidth\":1000,\"minHeight\":100,\"maxHeight\":1000},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"доступные расширения png, jpg, 10mb\",\"vn\":\"доступные расширения png, jpg, 10mb\",\"en\":\"доступные расширения png, jpg, 10mb\"}}}]},{\"id\":\"59by3sknr\",\"bindPath\":\"imgOne\",\"ui\":\"image\",\"subType\":\"image\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"value\":{\"type\":\"const\",\"value\":\"https://mail.sl-e.ru/skins/larry/images/roundcube_logo.png\"},\"validations\":[],\"width\":\"177\",\"height\":\"49\"}]},{\"id\":\"115295bd-f9d4-4750-834d-e60def05aac0\",\"ui\":\"container\",\"layout\":\"vertical\",\"c\":[{\"id\":\"of5go1zlp\",\"bindPath\":\"filePhoto\",\"ui\":\"file\",\"subType\":\"file\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"label\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фото документа\",\"vn\":\"Фото документа\",\"en\":\"Фото документа\"}},\"placeholder\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"Фото\",\"vn\":\"Фото\",\"en\":\"Фото\"}},\"help\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"загрузите файл\",\"vn\":\"загрузите файл\",\"en\":\"загрузите файл\"}},\"validations\":[{\"valid\":{\"type\":\"required\"},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"поле обязательное для заполнения!\",\"vn\":\"поле обязательное для заполнения!\",\"en\":\"поле обязательное для заполнения!\"}}},{\"valid\":{\"type\":\"uploadValid\",\"maxFileSize\":\"10mb\",\"extensions\":\"png, jpg\",\"minWidth\":100,\"maxWidth\":1000,\"minHeight\":100,\"maxHeight\":1000},\"error\":{\"type\":\"i18n\",\"i18n\":{\"ru\":\"доступные расширения png, jpg, 10mb\",\"vn\":\"доступные расширения png, jpg, 10mb\",\"en\":\"доступные расширения png, jpg, 10mb\"}}}]},{\"id\":\"g2s9mts4i\",\"bindPath\":\"imgTwo\",\"ui\":\"image\",\"subType\":\"image\",\"about\":\"\",\"defaultValue\":\"\",\"mask\":\"\",\"prefix\":\"\",\"suffix\":\"\",\"value\":{\"type\":\"const\",\"value\":\"https://mail.sl-e.ru/skins/larry/images/roundcube_logo.png\"},\"validations\":[],\"width\":\"114\",\"height\":\"60\"}]}]}],\"next\":\"Следующий шаг\",\"prev\":\"Предыдущий шаг\"}]";
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(IJsonDummy.class, new ElementGsonAdapter())
            .registerTypeAdapter(IJsonDummyValue.class, new ValueGsonAdapter())
            .registerTypeAdapter(IJsonDummyValidationCase.class, new ValidationCaseGsonAdapter())
            .create();

    /**
     * Парсит джейсон в список ядерных элементов
     * @param json джейсон с описанием элементов
     * @return список ядерных элементов, которые затем через адаптеры RUI отображаются на экране
     */
    public static List<IElement> parse(String json) {
        List<IElement> ret = new ArrayList<>();
        try {
            JsonArray js = new JsonParser().parse(json).getAsJsonArray();
            for (int i = 0; i < js.size(); ++i) {
                IJsonDummy root = GSON.fromJson(js.get(i).toString(), IJsonDummy.class);
                ret.add(root.getElement(null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getDefaultJson() {
        return JSON;
    }
}