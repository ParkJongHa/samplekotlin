package sample01_kotlin.demo006_gson

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

fun main1() {
    val cmsJsonElement = JsonParser.parseString(cmsJson)
    val apiBigCategoryList = cmsJsonElement.asJsonObject.get("entities").asJsonArray[0].asJsonObject.get("children").asJsonArray
    var cateInfo: JsonObject
    var cateApiList: JsonArray
    var anApiJObj: JsonObject
    var translatedApi: TranslatedApi


    for (aBigCategory in apiBigCategoryList.asList()) {
        cateInfo = aBigCategory.asJsonObject.get("entity").asJsonObject

        println(cateInfo)

        cateApiList = aBigCategory.asJsonObject.get("children").asJsonArray

        for (rawApi in cateApiList.asList()) {
            anApiJObj = rawApi.asJsonObject.get("entity").asJsonObject

            val header = anApiJObj.get("headers").asJsonArray
//                .map { it.asJsonObject.get("name").asString + ":" + it.asJsonObject.get("value").asString }
//                .joinToString(",")

            val query =
                try {
                    anApiJObj.get("uri").asJsonObject.get("query").asJsonObject.get("items").asJsonArray
//                        .map { it.asJsonObject.get("name").asString + "=" + it.asJsonObject.get("value").asString}
//                        .joinToString("&")
                } catch (_: Exception) {
                    JsonArray()//""
                }

            val body =
                try {
//                    val bodyType = anApiJObj.get("body").asJsonObject.get("bodyType").asString
                    anApiJObj.get("body").asJsonObject.get("formBody").asJsonObject.get("items").asJsonArray//.toString()//.get("formBody").asJsonObject.get("textBody").asString
//                    if ("Form" == bodyType) {
//                    } else { // Text
//                        JsonArray()
//                    }
                } catch (_: Exception) {
                    JsonArray()//""
                }

//            Gson().toJson()

            translatedApi = TranslatedApi(
                cateInfo.get("name").asString, // TM
                anApiJObj.get("name").asString, // "04 TM 멤버십 7일 무료 이벤트 적용"
                anApiJObj.get("method").asJsonObject.get("name").asString, // POST, GET ...
                header,
                anApiJObj.get("uri").asJsonObject.get("host").asString, // "cms-kr.superlozzi.com"
                anApiJObj.get("uri").asJsonObject.get("path").asString, // "/cms_sv/NA_TM_MEMBERSHIP_EVENT"
                query,
                body,
            )

            println(Gson().toJson(translatedApi))
//            println(translatedApi.header.map { it.asJsonObject.get("name").asString }.joinToString(","))
        }
    }
}

data class TranslatedApi(
    val bigCateName: String,
    val apiName: String,
    val methodType: String, // POST, GET...
    val header: JsonArray,
    val host: String, //   cms-kr.superlozzi.com
    val path: String, //   /cms_sv/NA_TM_MEMBERSHIP_EVENT
    val query: JsonArray,
    val body: JsonArray,
)

val cmsJson = """
    {
      "version": 6,
      "entities": [
        {
          "entity": {
            "type": "Project",
            "id": "e33e358b-7106-4f01-9363-2ed4607111e7",
            "name": "슈퍼로찌"
          },
          "children": [
            {
              "entity": {
                "type": "Service",
                "id": "295ae427-e28d-456d-a8ce-53e02991b74c",
                "name": "TM"
              },
              "children": [
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "mobile_no",
                            "value": "휴대폰번호"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_MOBILE"
                    },
                    "id": "673a0a39-ed10-4b9c-80da-3d9bebaee0e5",
                    "name": "01 회원 정보 보기 (휴대폰번호로 보기)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_email",
                            "value": "이메일주소"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_EMAIL"
                    },
                    "id": "bbb86394-e2de-47da-92d0-3d6a50ca6a57",
                    "name": "02 회원 정보 보기 (이메일로 보기)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_SUPER_LOZZI_CODE"
                    },
                    "id": "f7a8aaca-38b9-4311-a072-78ac5e0da937",
                    "name": "03 회원 정보 보기 (슈퍼로찌 코드로 보기)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "010-1234-5678"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_TM_MEMBERSHIP_EVENT"
                    },
                    "id": "b0ad8545-1e64-4eec-bffe-e5aa47a17105",
                    "name": "04 TM 멤버십 7일 무료 이벤트 적용",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "010-1234-5678"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_TM_MEMBERSHIP_EVENT_TIMER_SET"
                    },
                    "id": "f0418ff0-1ed5-4669-b8c6-f5b33cb80110",
                    "name": "05 TM 멤버십 7일 무료 이벤트 타이머 적용",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "mobile_no",
                            "value": "010-1234-5678"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_TM_MEMBERSHIP_EVENT_CHECK"
                    },
                    "id": "bd5f9ae9-50ab-4beb-af82-da7cea65af80",
                    "name": "06 TM 멤버십 대상자 확인",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "페이지 입력 첫페이지는 1 로 입력하고 조회 두번째 페이지는 2"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_MEMBERSHIP_UNSUBSCRIPT_LIST"
                    },
                    "id": "753467fe-49f8-442c-a66f-7f3d13e727d8",
                    "name": "07 멤버십 해지 대상자 확인",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "20231018055425000000"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                }
              ]
            },
            {
              "entity": {
                "type": "Service",
                "id": "8d6ae08c-ba4d-4db0-ad40-f5536434ca37",
                "name": "글로벌 SuperLozzi CMS"
              },
              "children": [
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_email",
                            "value": "leidycathe1107@gmail.com"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_email",
                            "value": "dkdic80@gmail.com"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_EMAIL"
                    },
                    "id": "6b061dfa-bb2a-4fa5-8cf7-268dcd0e7287",
                    "name": "01 회원 정보 보기 (이메일)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_email",
                            "value": "leidycathe1107@gmail.com"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_code",
                            "value": "71ZE1AE"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_USER_CODE"
                    },
                    "id": "60159269-4ce6-47eb-bded-1110d076b86e",
                    "name": "02 회원 정보 보기 (슈퍼로찌 코드)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_email",
                            "value": "leidycathe1107@gmail.com"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_email",
                            "value": "leidycathe1107@gmail.com"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_BAN_LIST_BY_EMAIL"
                    },
                    "id": "a7a7f638-b35e-493d-8f15-73f2defb3d3c",
                    "name": "03 차단 유저인지 확인 (이메일)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_email",
                            "value": "leidycathe1107@gmail.com"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_code",
                            "value": "71ZE1AE"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_BAN_LIST_BY_USER_CODE"
                    },
                    "id": "5c388524-6d18-4976-84ff-d1336d97f76b",
                    "name": "04 차단 유저인지 확인 (슈퍼로찌 코드)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_email",
                            "value": "dkdic80@gmail.com"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "이곳은 제목입니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "이곳은 내용입니다."
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_PUSH_BY_EMAIL"
                    },
                    "id": "23f2db48-c7ab-4f2e-a93b-9aae4f372833",
                    "name": "05 푸시 보내기 (이메일 주소)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "locale=en-US|lang_code=en|country_code=US|version_code=1|os_type=os_tp_android|version_name=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": "71CVAEV"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "이곳은 제목입니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "이곳은 내용입니다."
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_PUSH_BY_USER_CODE"
                    },
                    "id": "0f3385a0-c5c4-443d-8775-d9fcb4600bf6",
                    "name": "06 푸시 보내기 (슈퍼로찌 코드)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "locale=en-US|lang_code=en|country_code=US|version_code=1|os_type=os_tp_android|version_name=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi - Drawing will be held in the global Super App tomorrow as well!"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "Look forward to seize the chance to win an exclusive prize money tomorrow!"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "push_type",
                            "value": "LOZZI_SHOPPING"
                          },
                          {
                            "type": "Text",
                            "name": "has_img",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "img_url",
                            "value": "https://file.superlozzi.com/public/shopping_popup_character.png"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "is_lending",
                            "value": "Y"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "lending_url",
                            "value": "https://www.superlozzi.com"
                          },
                          {
                            "type": "Text",
                            "name": "country_cd_yn",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "country_cd",
                            "value": "KR"
                          },
                          {
                            "type": "Text",
                            "name": "zone_off_set_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "zone_off_set",
                            "value": "1000"
                          },
                          {
                            "type": "Text",
                            "name": "version_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "version",
                            "value": "0"
                          },
                          {
                            "type": "Text",
                            "name": "os_type_yn",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "os_type",
                            "value": "ANDROID"
                          },
                          {
                            "type": "Text",
                            "name": "lang_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "lang",
                            "value": "AA"
                          },
                          {
                            "type": "Text",
                            "name": "age_yn",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "age",
                            "value": "10"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_PUSH_ADD"
                    },
                    "id": "762309f2-0bf7-4bea-9e2d-6d41430910c4",
                    "name": "07 푸시 등록하기 (로찌쇼핑 웹에서 링크 열기)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi - Drawing will be held in the global Super App tomorrow as well!"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "Look forward to seize the chance to win an exclusive prize money tomorrow!"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "push_type",
                            "value": "WEB_INTERNAL"
                          },
                          {
                            "type": "Text",
                            "name": "has_img",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "img_url",
                            "value": "https://file.superlozzi.com/public/shopping_popup_character.png"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "is_lending",
                            "value": "Y"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "lending_url",
                            "value": "https://www.superlozzi.com"
                          },
                          {
                            "type": "Text",
                            "name": "country_cd_yn",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "country_cd",
                            "value": "KR"
                          },
                          {
                            "type": "Text",
                            "name": "zone_off_set_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "zone_off_set",
                            "value": "1000"
                          },
                          {
                            "type": "Text",
                            "name": "version_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "version",
                            "value": "0"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "os_type_yn",
                            "value": "Y"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "os_type",
                            "value": "IOS"
                          },
                          {
                            "type": "Text",
                            "name": "lang_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "lang",
                            "value": "AA"
                          },
                          {
                            "type": "Text",
                            "name": "age_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "age",
                            "value": "10"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_PUSH_ADD"
                    },
                    "id": "4af7c04e-37ac-4b44-8caa-0d94fea08ef0",
                    "name": "08 푸시 등록하기 (앱 내부 새창에서 링크 열기)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi - Drawing will be held in the global Super App tomorrow as well!"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "Look forward to seize the chance to win an exclusive prize money tomorrow!"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "push_type",
                            "value": "WEB_EXTERNAL"
                          },
                          {
                            "type": "Text",
                            "name": "has_img",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "img_url",
                            "value": "https://file.superlozzi.com/public/shopping_popup_character.png"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "is_lending",
                            "value": "Y"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "lending_url",
                            "value": "https://www.superlozzi.com"
                          },
                          {
                            "type": "Text",
                            "name": "country_cd_yn",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "country_cd",
                            "value": "KR"
                          },
                          {
                            "type": "Text",
                            "name": "zone_off_set_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "zone_off_set",
                            "value": "1000"
                          },
                          {
                            "type": "Text",
                            "name": "version_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "version",
                            "value": "0"
                          },
                          {
                            "type": "Text",
                            "name": "os_type_yn",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "os_type",
                            "value": "ANDROID"
                          },
                          {
                            "type": "Text",
                            "name": "lang_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "lang",
                            "value": "AA"
                          },
                          {
                            "type": "Text",
                            "name": "age_yn",
                            "value": "N"
                          },
                          {
                            "type": "Text",
                            "name": "age",
                            "value": "10"
                          }
                        ]
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_PUSH_ADD"
                    },
                    "id": "cb8d3080-6643-4520-aa90-f0ced85381e4",
                    "name": "09 푸시 등록하기 (크롬에서 링크 열기)",
                    "headers": [
                      {
                        "name": "User-Agent",
                        "value": "versionCode=1-osType=os_tp_android-versionName=1.1"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_email",
                            "value": "dk@svcorps.dev"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "500"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_ADD"
                    },
                    "id": "effce9ea-fb89-405e-9c85-b64490f625fb",
                    "name": "21 추가 보너스 조합 임시로 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_LIST"
                    },
                    "id": "c87cdb22-a6b4-4cfe-a923-c3c6d3297bea",
                    "name": "22 추가 보너스 조합 임시 등록 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "cms_bonus_target_no",
                            "value": "3"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "100"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_MODIFY"
                    },
                    "id": "5b81787a-201d-4806-90b8-000e6f6da34c",
                    "name": "23 추가 보너스 조합 대상자 수정하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "type": "Text",
                            "name": "cms_bonus_target_no",
                            "value": "2"
                          },
                          {
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "100"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-us.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_PROCESS"
                    },
                    "id": "a087eab2-9f9a-4057-9629-556bee9f24ff",
                    "name": "24 추가 보너스 조합 대상자 최종 반영하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                }
              ]
            },
            {
              "entity": {
                "type": "Service",
                "id": "065eb45f-cc11-46d5-9b68-2fccd7f199c0",
                "name": "한국 SalesVook CMS"
              },
              "children": [
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_SV_CMS_COMMENT_CD_ID"
                    },
                    "id": "e144d32a-3088-41e0-a4ab-22e0c00a8054",
                    "name": "01 [게시판] 고유 키 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "comment_cd_id",
                            "value": "SV_CM_UNIT_MARKET"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WISH_LIST"
                    },
                    "id": "3851e3b4-08f9-48b2-a37b-2bdae3cebb09",
                    "name": "02 [게시판] 메인글 데이터 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "comment_cd_id",
                            "value": "SV_CM_UNIT_MARKET"
                          },
                          {
                            "enabled": true,
                            "name": "my_wish_main_no",
                            "value": "30230704131658229222"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_REPLY_LIST"
                    },
                    "id": "e0a1c7a8-4096-45d2-be55-eea84b7e3ff5",
                    "name": "03 [게시판] 댓글 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_main_no",
                            "value": "2"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "comment_cd_id",
                            "value": ""
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_sub_no",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WISH_BLIND"
                    },
                    "id": "63bead77-145c-4b2e-9467-897bda125297",
                    "name": "04 [게시판] 안보이게 처리하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_REAL_TIME_FT_RANK"
                    },
                    "id": "d08d8d31-8345-4748-8e03-b6dee6ccf68d",
                    "name": "10 [실시간] FT 실시간 랭킹 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_PW_INIT_LIST"
                    },
                    "id": "45646c6b-9606-491d-8c7f-05d0a2703bfa",
                    "name": "1001 [회원정보] 비밀번호 초기화 대기 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_PW_INIT_ADD"
                    },
                    "id": "20ebabe5-a169-447b-bb5d-bf8871ae1066",
                    "name": "1002 [회원정보] 비밀번호 초기화 대기 리스트에 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_PW_INIT_ADJUST"
                    },
                    "id": "f902ce40-272f-4b6e-8cd0-d700eaeff84e",
                    "name": "1003 [회원정보] 비밀번호 초기화 대상자 최종 반영하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_PW_INIT_REMOVE"
                    },
                    "id": "e385f873-65ca-451f-8ee0-05f5c6cbcfe0",
                    "name": "1004 [회원정보] 비밀번호 초기화 대기 리스트에서 제거하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품 고유 코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "pass_vook_cnt_max",
                            "value": "확정 패스권 수량"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_amount_cev",
                            "value": "마진액"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_calc_value",
                            "value": "분배상수"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_max_issue",
                            "value": "계정당 발급 갯수 [ 0 이면 무한대 ]"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_info_open_yn",
                            "value": "결과 정보 공개 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "quiz_yn",
                            "value": "퀴즈 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "quiz_tp_cd_id",
                            "value": "퀴즈 종류"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_META_INFO_CHANGE"
                    },
                    "id": "cf8d79fa-74eb-402b-9e33-53d808d62007",
                    "name": "21 [WAVE] 제품 메타 정보 수정",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_STOP_RESERVATION"
                    },
                    "id": "10871f0d-204a-4a5a-8502-8f6ccf634c98",
                    "name": "22 [WAVE] 웨이브 자동 시작 중지하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "reservation_yn",
                            "value": "다음 웨이브 자동 실행 여부 Y/N"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_LIVE_RESERVATION"
                    },
                    "id": "ffb845be-53f0-4ff8-863e-98bc82ae6091",
                    "name": "23 [WAVE] 웨이브 라이브 예약하기 (자동 시작 중지된것 ON 할 때도 쓰임)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "order_no",
                            "value": "주문번호"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_CANCEL"
                    },
                    "id": "57e4b36e-c818-4701-b942-52bcb10d1a31",
                    "name": "26 [WAVE] 주문 취소 적용하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_no",
                            "value": "회원 고유 번호"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "order_no",
                            "value": "주문번호"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_ORDER_FORCE_MAPPING"
                    },
                    "id": "3a44d62b-5c81-4d88-8047-9d37b4e573f2",
                    "name": "28 [WAVE] 주문 번호 강제로 반영하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": []
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "kr-cdn.salesvook.app",
                      "path": "/cdn/v1/86400/UNIT_CATEGORY_LIST"
                    },
                    "id": "6f9af420-c7e9-476a-9d30-b0255cebed6b",
                    "name": "30 [WAVE] 카테고리 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=1|osType=os_tp_ios|versionName=1.1|device_id=811fe27b-665a-44bd-bf3d-6e558f7378fe"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      },
                      {
                        "enabled": true,
                        "name": "X-Locale",
                        "value": "ko-KR"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Language",
                        "value": "ko-KR"
                      },
                      {
                        "enabled": true,
                        "name": "USER-TIMEZONE",
                        "value": "Asia/Seoul"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": []
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_DELIVERY_CODE_INFO"
                    },
                    "id": "ccd098b7-1f6e-49bc-8cdc-24e2e6f9d740",
                    "name": "31 [WAVE] 배송 유형 코드 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=1|osType=os_tp_ios|versionName=1.1|device_id=811fe27b-665a-44bd-bf3d-6e558f7378fe"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Language",
                        "value": "ko-KR"
                      },
                      {
                        "enabled": true,
                        "name": "USER-TIMEZONE",
                        "value": "Asia/Seoul"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": []
                      },
                      "bodyType": "Form"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_QUIZ_CODE_INFO"
                    },
                    "id": "c88d793c-2975-458e-9bc3-90802b37920b",
                    "name": "31 [WAVE] 퀴즈 코드 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=1|osType=os_tp_ios|versionName=1.1|device_id=811fe27b-665a-44bd-bf3d-6e558f7378fe"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Language",
                        "value": "ko-KR"
                      },
                      {
                        "enabled": true,
                        "name": "USER-TIMEZONE",
                        "value": "Asia/Seoul"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_UNIT_META_TEMP_LIST"
                    },
                    "id": "5c8b2742-1db4-43fc-9060-cb5e746e3845",
                    "name": "32 [WAVE] 제품 메타 정보 임시 저장소 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "category_cd_id",
                            "value": "카테고리 코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_nm",
                            "value": "상품명"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_desc",
                            "value": "상품 설명"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_amount",
                            "value": "상품 가격"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "pass_vook_cnt_max",
                            "value": "확정 패스권 수량"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_calc_value",
                            "value": "분배상수"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mod_cnt_max",
                            "value": "배송 가능 수량(MOQ)"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_amount_cev",
                            "value": "개당 분배할 금액"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "iam_web_prod_no",
                            "value": "아임웹 상품 번호"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_max_issue",
                            "value": "계정당 발급 갯수 [ 0 이면 무한대 ]"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_info_open_yn",
                            "value": "결과 정보 공개 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "quiz_yn",
                            "value": "퀴즈 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "quiz_tp_cd_id",
                            "value": "퀴즈 종류"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_dtl_cnt",
                            "value": "제품 상세 이미지 파일 갯수"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_dtl_sub_nm",
                            "value": "제품 상세 이미지 파일에 있는 값. 예) wave_d_d_SV304823072700001_230727_1_1 에서 230727_1 를 뜻함. 없으면 빈값 입력"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_tp_sub_cd_id",
                            "value": "유닛 종류 [ WAVE_TP_SUB_0001 : 유닛,  WAVE_TP_SUB_0100 : 디지털 유닛,  WAVE_TP_SUB_0200 : 레어 유닛 ]"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "dlvy_tp_no",
                            "value": "배송유형 코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "freeze_wave_yn",
                            "value": "프리즈 웨이브 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "freeze_amount_max_multiple",
                            "value": "프리즈 몇배??? 100 이면 (prod_amount - prod_amount_cev) 곱하기 100 의 값이 최대치 (만약 프리즈가 아니면 왼쪽 체크 해제)"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "freeze_week_apply",
                            "value": "프리즈 증여 신청 가능 몇주?? 6개월이면 26 입력 그러면 27주차때 증여 신청 가능 (만약 프리즈가 아니면 왼쪽 체크 해제)"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "freeze_week_max",
                            "value": "프리즈 몇주?? 1년이면 52 입력 그러면 53주차까지 증가함 (만약 프리즈가 아니면 왼쪽 체크 해제)"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_MAKE_UNIT_META_TEMP"
                    },
                    "id": "6107004d-ec41-4cf4-a919-f48766123810",
                    "name": "33 [WAVE] 제품 메타 정보 임시 저장소에 넣기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "category_cd_id",
                            "value": "카테고리 코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_nm",
                            "value": "상품명"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_desc",
                            "value": "상품 설명"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_amount",
                            "value": "상품 가격"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "pass_vook_cnt_max",
                            "value": "확정 패스권 수량"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_calc_value",
                            "value": "분배상수"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mod_cnt_max",
                            "value": "배송 가능 수량(MOQ)"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "prod_amount_cev",
                            "value": "개당 분배할 금액"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "iam_web_prod_no",
                            "value": "아임웹 상품 번호"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_max_issue",
                            "value": "계정당 발급 갯수 [ 0 이면 무한대 ]"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_info_open_yn",
                            "value": "결과 정보 공개 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "quiz_yn",
                            "value": "퀴즈 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "quiz_tp_cd_id",
                            "value": "퀴즈 종류"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_change_yn",
                            "value": "이미지 교체 여부 Y/N"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_banner",
                            "value": "웨이브 리스트 [사각형 타입] 이미지 파일명. png 제외하고 파일명만 입력"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_main",
                            "value": "웨이브 메인 [원형 타입] 이미지 파일명. png 제외하고 파일명만 입력"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_unit",
                            "value": "유닛 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_dtl_cnt",
                            "value": "제품 상세 이미지 파일 갯수"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "img_dtl_1",
                            "value": "1번 제품 상세 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_2",
                            "value": "2번 제품 상세 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_3",
                            "value": "3번 제품 상세 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_4",
                            "value": "4번 제품 상세 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_5",
                            "value": "5번 제품 상세 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_6",
                            "value": "6번 제품 상세 이미지 파일명 png 제외하고 파일명만 입력"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_7",
                            "value": "wave_d_d_SV278223070300001_230727_2_7"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_8",
                            "value": "wave_d_d_SV278223070300001_230727_2_8"
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_9",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_10",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_11",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_12",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_13",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_14",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_15",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_16",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_17",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_18",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_19",
                            "value": ""
                          },
                          {
                            "type": "Text",
                            "name": "img_dtl_20",
                            "value": ""
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_tp_sub_cd_id",
                            "value": "유닛 종류 [ WAVE_TP_SUB_0001 : 유닛,  WAVE_TP_SUB_0100 : 디지털 유닛,  WAVE_TP_SUB_0200 : 레어 유닛 ]"
                          },
                          {
                            "type": "Text",
                            "name": "dlvy_tp_no",
                            "value": "배송유형 코드"
                          },
                          {
                            "type": "Text",
                            "name": "freeze_wave_yn",
                            "value": "프리즈 웨이브 여부 Y/N"
                          },
                          {
                            "type": "Text",
                            "name": "freeze_amount_max_multiple",
                            "value": "프리즈 몇배??? 100 이면 (prod_amount - prod_amount_cev) 곱하기 100 의 값이 최대치"
                          },
                          {
                            "type": "Text",
                            "name": "freeze_week_apply",
                            "value": "프리즈 증여 신청 가능 몇주?? 6개월이면 26 입력 그러면 27주차때 증여 신청 가능 (만약 프리즈가 아니면 왼쪽 체크 해제)"
                          },
                          {
                            "type": "Text",
                            "name": "freeze_week_max",
                            "value": "프리즈 몇주?? 1년이면 52 입력 그러면 53주차까지 증가함 (만약 프리즈가 아니면 왼쪽 체크 해제)"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_MODIFY_UNIT_META_TEMP"
                    },
                    "id": "109f8f1d-2c21-41ce-90e1-737c9a6f078c",
                    "name": "34 [WAVE] 제품 메타 정보 임시 저장소 수정하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_UNIT_META_TEMP_CONFIRM"
                    },
                    "id": "04af6368-019f-4b23-97f9-d4f1a9550611",
                    "name": "35 [WAVE] 제품 메타 정보 임시 저장소 최종 확인하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_UNIT_META_TEMP_TO_REAL"
                    },
                    "id": "64e60620-4dd3-4da1-b0fc-5ae15b7505b5",
                    "name": "36 [WAVE] 웨이브 제품 임시 저장소에서 실 데이터 반영하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_UNIT_META_LIST"
                    },
                    "id": "7f314d6b-3054-4624-bd62-1d6b116a2d74",
                    "name": "36 [WAVE] 제품 메타 정보 실제 데이터 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "reservation_yn",
                            "value": "자동 실행(예약) 여부 Y/N"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_UNIT_META_START_WAVE_FIRST_TIME"
                    },
                    "id": "ede71ea6-06da-4593-98dc-f81b6afa3250",
                    "name": "37 [WAVE] 처음 등록한 제품 웨이브 시작하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "unit_meta_no",
                            "value": "제품코드"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_STOP"
                    },
                    "id": "33c5d8b5-8d44-49ad-be17-ac3c197e1145",
                    "name": "38 [WAVE] 지금 돌고 있는 웨이브 OFF 하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "force_yn",
                            "value": "강제로 취소하기 (CMS 로 구독시켜준 유저 케이스) Y/N"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_MEMBERSHIP_CANCEL"
                    },
                    "id": "4a754eeb-9da6-458c-8550-b5207d9ea09e",
                    "name": "40 [멤버십] 구독 취소하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "membership_tp_cd_id",
                            "value": "SV_MEMBER_SHIP_A0005"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "card_number",
                            "value": "아무거나 카드번호"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "birth_info",
                            "value": "아무거나 생년월일"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "card_pass",
                            "value": "아무거나 \b카드 비밀번호 앞 2자리"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "card_invalid_yy",
                            "value": "아무거나 카드 년도 2자리"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "card_invalid_mm",
                            "value": "아무거나 카드 월 2자리"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_MEMBERSHIP_SUBSCRIBE"
                    },
                    "id": "e1de53d9-0ebe-4ac9-b43c-39318aaa577b",
                    "name": "41 [멤버십] 블랙 멤버십 구독하기 (다음 달 예약은 안됨.)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "billing_history_no",
                            "value": "20230824150611001078"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "billing_history_no_next",
                            "value": "20230824150612001079"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_BILLING_CANCEL"
                    },
                    "id": "2647672f-4322-4d69-a163-dd80dbee126b",
                    "name": "42 [멤버십] 결제 취소하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "billing_history_no",
                            "value": "결제번호"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_MEMBERSHIP_INFO_BY_BILLING_NO"
                    },
                    "id": "83b0896d-387b-4c95-a1df-cea903051e63",
                    "name": "43 [멤버십] 결제 번호로 멤버십 여부 확인하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "order_id",
                            "value": "주문 번호"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_WAVE_RESULT_REFUND"
                    },
                    "id": "c00bb08f-36c5-4fa4-914b-4ae0106edc77",
                    "name": "60 [WAVE] 웨이브 결과 환불하고 EV, FT, 유닛 회수하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "imp_uid",
                            "value": "imps_425356376625"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "amount",
                            "value": "9900"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_BILLING_CANCEL_TEST"
                    },
                    "id": "589e5257-eeae-4629-912e-6402c1a0d13c",
                    "name": "TEST 42 [멤버십] 결제 취소하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "10040004800000010702"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_HDRY_COMPANY_LIST"
                    },
                    "id": "1dbb552b-3c6d-485d-b19c-d784303548e6",
                    "name": "[DELIVERY] 01 택배사 리스트 조회",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "name": "dlvy_no",
                            "value": "배송 번호"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_DELIVERY_STAND_BY"
                    },
                    "id": "d5b4afbc-73cb-4564-be49-265d5eb8d5d8",
                    "name": "[DELIVERY] 02 배송 대기 리스트 조회",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "dlvy_no",
                            "value": "배송 번호"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "waybil_no",
                            "value": "운송장 번호"
                          },
                          {
                            "type": "Text",
                            "name": "hdry_company_cd",
                            "value": "택배사 코드 (CJ : HCC00002) 우선 여기 항상 CJ 로 하는거로 알기 때문에 체크 해제해도 됨."
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_DELIVERY_SHIPPING"
                    },
                    "id": "2b9f54ae-c562-4b6a-93e2-69032e0bdad9",
                    "name": "[DELIVERY] 03 배송중 처리하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "name": "dlvy_no",
                            "value": "배송 번호"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_DELIVERY_SHIPPING_LIST"
                    },
                    "id": "b8e126af-ec4e-4a57-8084-e67e9d9111c9",
                    "name": "[DELIVERY] 04 배송 중 리스트 조회",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "dlvy_no",
                            "value": "배송 번호"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_DELIVERY_SHIPPING_FINISH"
                    },
                    "id": "4067ed8c-751c-4e38-b355-439256ab87c5",
                    "name": "[DELIVERY] 05 배송 완료 처리하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "name": "dlvy_no",
                            "value": "배송 번호"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_DELIVERY_CANCEL_APPLY_LIST"
                    },
                    "id": "86688fce-9ad2-44a4-9ea2-865ef7ef967d",
                    "name": "[DELIVERY] 06 배송 취소 요청 리스트 조회",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "dlvy_no",
                            "value": "배송 번호"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_DELIVERY_CANCEL"
                    },
                    "id": "f9fe6d42-994e-4e34-84a8-8d9ac40d96d2",
                    "name": "[DELIVERY] 07 배송 취소 처리하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_COMMENT_BLACK_LIST"
                    },
                    "id": "c7fc534d-b595-4f2a-a7b7-02ba5983c274",
                    "name": "[게시판] 블랙 리스트 유저 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_REMOVE_COOMMENT_BLACK_LIST"
                    },
                    "id": "71f5bd09-0bc4-4ee0-bf60-5449900eb0c3",
                    "name": "[게시판] 블랙 리스트 유저 해제하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_CMS_ADD_COOMMENT_BLACK_LIST"
                    },
                    "id": "35770ba1-189e-4cea-925c-56dd4a9ed72f",
                    "name": "[게시판] 블랙 리스트로 유저 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                }
              ]
            },
            {
              "entity": {
                "type": "Service",
                "id": "a7895f63-4c79-461c-a555-457b639b837f",
                "name": "한국 SuperLozzi CMS"
              },
              "children": [
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "010-1234-5678"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms_sv/NA_TM_MEMBERSHIP_EVENT_BONUS"
                    },
                    "id": "2534e7a3-2b70-4bf5-b2b7-782bc0b14f5e",
                    "name": "06 TM 멤버십 7일 무료 이벤트 대상자 보너스 번호조합 지급",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00100000000000010010"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_KEY_INFO"
                    },
                    "id": "afab72c4-c14f-4d2d-89fa-70f699ff84bc",
                    "name": "[WAVE] 관련 키 정보 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_TEMP_LIST"
                    },
                    "id": "be56186c-0ae7-4f3d-9cd5-9bf194b78ae6",
                    "name": "[WAVE] 대기 저장소 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_dt_start",
                            "value": "2022-11-30 12"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_dt_end",
                            "value": "2022-11-30 13"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_tp_cd_id",
                            "value": "WAVE_TP_2301"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "banner_prod_cnt",
                            "value": "20"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "banner_prod_amount",
                            "value": "12300"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "banner_img",
                            "value": "WAVE_BG_0008"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "deposit_yn",
                            "value": "Y"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "deposit_point",
                            "value": "1000"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_nm",
                            "value": "제품명 6"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_price",
                            "value": "제품별 상이"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_url",
                            "value": "https://www.naver.com"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "10588250,10288149,10521854,10587634,10305096,10569035,10290262,10488256,10587927,3540880,10572597,10588566,10587938,10568281,10312400,3548985,10573824,10572409,10526012,3550394,10527822,10586284,3548358,10526713,10292422,10587431,10568126,10299760"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_amount_avg",
                            "value": "0"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_ADD_TEMP"
                    },
                    "id": "59ccf2a8-9c21-4fdc-b2f2-6282062d98d6",
                    "name": "[WAVE] 대기 저장소에 넣기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_seq_no",
                            "value": "1125"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_ADD"
                    },
                    "id": "e202614d-cca0-4dfe-8ab5-4ed64662bbf9",
                    "name": "[WAVE] 대기 저장소에서 마지막 컨펌 단계 직전까지 가기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_seq_no",
                            "value": "4628"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "b4fzvpyjgv6yafvoompiegte340onruj.lambda-url.ap-northeast-2.on.aws",
                      "path": "/cms/NA_CMS_WAVE_ADD"
                    },
                    "id": "1401bfb1-338f-422b-9e01-0f6d4a2037b7",
                    "name": "[WAVE] 대기 저장소에서 마지막 컨펌 단계 직전까지 가기(구글 시트)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_seq_no",
                            "value": "1"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_REMOVE_FROM_TEMP_LIST"
                    },
                    "id": "ec556a26-c9ee-4161-8c45-bf65266fdb47",
                    "name": "[WAVE] 대기 저장소에서 제거하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_READY_LIST"
                    },
                    "id": "94a97606-07c2-41bc-a0b9-0f85c0ac55ca",
                    "name": "[WAVE] 라이브 예고 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_FINISH_LIST"
                    },
                    "id": "bb50c135-993a-4a32-9071-2b5dcc63c42e",
                    "name": "[WAVE] 라이브 종료 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_PROGRESS_LIST"
                    },
                    "id": "402b2e10-f730-4e7c-a782-d6d48fd49781",
                    "name": "[WAVE] 라이브 진행중 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_BLACK_LIST_ADD"
                    },
                    "id": "9a529754-4d6d-4ffb-b945-0534a3010925",
                    "name": "[WAVE] 블랙 리스트 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "mobile_no",
                            "value": "01051304546"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_BLACK_USER_CHECK"
                    },
                    "id": "5e0a0b38-2a6c-4a5a-b6e5-18a5764ab2f2",
                    "name": "[WAVE] 블랙 리스트 유저 확인하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_BLACK_LIST_REMOVE"
                    },
                    "id": "7d3c62ce-ee24-4161-88dc-4f698f4a747e",
                    "name": "[WAVE] 블랙 리스트 제거하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "10592214"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_ADD_PRODUCT_ID"
                    },
                    "id": "7d3f1e70-1f87-4d67-8d17-1406a4975b95",
                    "name": "[WAVE] 상품 코드 추가하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_no",
                            "value": "SV221125013222000036KR"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_REMOVE"
                    },
                    "id": "4620d0f3-2648-48ca-a4fc-3dcb68a66ee1",
                    "name": "[WAVE] 실수로 올렸을 때 빠르게 삭제하기. 누가 구매했을 때 이슈 생길 수 있음.",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_no",
                            "value": "SV221222132103001275KR"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_ADD_CONFIRM"
                    },
                    "id": "1f2f9ac4-6750-4784-9e8e-75814606e76b",
                    "name": "[WAVE] 최종 컨펌하기 (바로 라이브되어서 유저에게 노출됨.)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_CONFIRM_LIST"
                    },
                    "id": "ba743f4d-5c2e-4b7b-89b4-772cf201b034",
                    "name": "[WAVE] 컨펌 대기 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_no",
                            "value": "SV221125013222000036KR"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_REMOVE_FROM_CONFIRM_LIST"
                    },
                    "id": "8153eafc-4324-46bc-a975-3df8b1441758",
                    "name": "[WAVE] 컨펌 대기 리스트에서 삭제하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_ymd_from",
                            "value": "2022-12-22"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wave_ymd_to",
                            "value": "2022-12-23"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WAVE_COPY_TEMP_YMD_FROM_TO"
                    },
                    "id": "83603546-7809-4fdb-ac89-f8506b6aa202",
                    "name": "[WAVE] 특정 일자 하루치 복제해서 대기 저장소에 넣기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "comment_cd_id",
                            "value": "COMMENT_WAVE_22_11_15_01"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WITH_READY_LIST"
                    },
                    "id": "34dde2f6-6a62-41e8-928d-fb237198478a",
                    "name": "[게시판] 검수용 데이터 가져오기 (웨이브 진행건도 포함됨)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_COMMENT_CD_ID"
                    },
                    "id": "347bcdc7-1ecd-4457-8738-1e5b6d972106",
                    "name": "[게시판] 고유 키 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "comment_cd_id",
                            "value": "CM_AMTER"
                          },
                          {
                            "enabled": true,
                            "name": "my_wish_main_no",
                            "value": "30230506011305089637"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_REPLY_LIST"
                    },
                    "id": "fa725396-30b2-4600-b1dc-ee786197d15f",
                    "name": "[게시판] 댓글 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "name": "comment_cd_id",
                            "value": "CM_AMTER"
                          },
                          {
                            "name": "user_no",
                            "value": "703166"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WISH_LIST"
                    },
                    "id": "08a9ed08-708e-4876-a49a-070831e30ec2",
                    "name": "[게시판] 데이터 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_COMMENT_BLACK_LIST"
                    },
                    "id": "fafa53f3-219b-4d89-9fa0-b31fd6215293",
                    "name": "[게시판] 블랙 리스트 유저 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_REMOVE_COOMMENT_BLACK_LIST"
                    },
                    "id": "c5f03e22-be04-42a7-872a-a6ca69d7abd8",
                    "name": "[게시판] 블랙 리스트 유저 해제하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "user_code",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_ADD_COOMMENT_BLACK_LIST"
                    },
                    "id": "ce825151-19e8-4f9e-9d34-a702f9cfbf89",
                    "name": "[게시판] 블랙 리스트로 유저 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_main_no",
                            "value": "2"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "comment_cd_id",
                            "value": ""
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_sub_no",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WISH_BLIND"
                    },
                    "id": "1c967eac-9ae4-4d44-8250-a8a51d437a54",
                    "name": "[게시판] 안보이게 처리하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "multipart/form-data",
                        "items": [
                          {
                            "type": "File",
                            "name": "file"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_MAKE_SHOP_ORDER_INVOICE"
                    },
                    "id": "9e8c0834-a7ec-40cf-8a64-1b2ea93afbba",
                    "name": "[메이크샵] 송장 입력하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "multipart/form-data"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_code",
                            "value": "71VMD2"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_COMMON_GOLD_ISSUE_LIST"
                    },
                    "id": "e2ed7eb8-200a-4f12-b689-e78b6cf27648",
                    "name": "[번호조합] 보너스 일반/황금 번호조합 최근 지급 히스토리 조회하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "push_reserve_no",
                            "value": "67"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_RESERVE_PUSH_REMOVE"
                    },
                    "id": "a86c459b-f3b5-464f-8c1d-14012c044eb5",
                    "name": "[예약푸시] 예약 취소하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_RESERVE_PUSH_LIST"
                    },
                    "id": "29deb356-49b4-4859-9ebb-0ff80f5a6c58",
                    "name": "[예약푸시] 예약 푸시 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "reserve_dt",
                            "value": "2022-11-18 11:40"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "출금신청 결과안내"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "카카오뱅크 미니 카드를 사용하신다면 예금주명을 'ㅇㅇㅇ(미니)'으로 입력하셔서 다시 신청해 주시기 바랍니다."
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_ADD_PARTITION_PUSH"
                    },
                    "id": "9b33e7f1-a204-4bb5-b7b6-702acb6615be",
                    "name": "[예약푸시] 푸시 예약하기 (O/S 전체)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "reserve_dt",
                            "value": "2022-11-18 11:40"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "출금신청 결과안내"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "카카오뱅크 미니 카드를 사용하신다면 예금주명을 'ㅇㅇㅇ(미니)'으로 입력하셔서 다시 신청해 주시기 바랍니다."
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_ADD_PARTITION_PUSH_IOS"
                    },
                    "id": "1ce0457d-1644-41ca-a535-8520e4bf158c",
                    "name": "[예약푸시] 푸시 예약하기 (아이폰)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "reserve_dt",
                            "value": "2022-11-18 11:40"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "출금신청 결과안내"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "카카오뱅크 미니 카드를 사용하신다면 예금주명을 'ㅇㅇㅇ(미니)'으로 입력하셔서 다시 신청해 주시기 바랍니다."
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_ADD_PARTITION_PUSH_ANDROID"
                    },
                    "id": "fc70e4b2-b87e-4474-a370-97384f6c8180",
                    "name": "[예약푸시] 푸시 예약하기 (안드로이드)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546,01040725052"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "err_tp",
                            "value": "ACCN"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_PUSH_WITHDRAWAL_ERR"
                    },
                    "id": "04488850-801d-4393-81a1-fe491eb2b5df",
                    "name": "[입금 오류 푸시] 은행 오류 푸시 보내기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01040725052,01051304546,01044735130"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "err_tp",
                            "value": "MINI"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_PUSH_WITHDRAWAL_ERR"
                    },
                    "id": "f9386d1c-54b2-4dbc-b580-51bf13bd826c",
                    "name": "[입금 오류 푸시] 카카오 미니 오류 푸시 보내기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546,01044735130"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "err_tp",
                            "value": "TOSS"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_PUSH_WITHDRAWAL_ERR"
                    },
                    "id": "fcd17a9b-1910-4fac-bd02-232400a9091c",
                    "name": "[입금 오류 푸시] 토스 오류 푸시 보내기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "mobile_no",
                            "value": "휴대폰번호"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_MOBILE"
                    },
                    "id": "5a86e91f-4f6b-4d16-bd0a-666d399a4ae0",
                    "name": "슈퍼로찌 : 01 회원 정보 보기 (휴대폰번호로 보기)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_email",
                            "value": "이메일주소"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_EMAIL"
                    },
                    "id": "f03d8c0d-70ba-4ab3-958e-f035e928d4e7",
                    "name": "슈퍼로찌 : 02 회원 정보 보기 (이메일로 보기)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "user_code",
                            "value": "슈퍼로찌코드"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_BY_SUPER_LOZZI_CODE"
                    },
                    "id": "16c378d2-46e6-4413-b103-d09a2ae61a17",
                    "name": "슈퍼로찌 : 03 회원 정보 보기 (슈퍼로찌 코드로 보기)",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "mobile_no",
                            "value": "01051304546"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_INFO_ALL_BY_MOBILE"
                    },
                    "id": "ef5a591c-c494-4b29-98bf-06390ce892ed",
                    "name": "슈퍼로찌 : 04 휴대폰 번호로 가입한적있는 이메일 전체 조회",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_USER_BAN_LIST_ALL"
                    },
                    "id": "3ca22b76-7c33-4d43-8af3-8684056a086a",
                    "name": "슈퍼로찌 : 05 차단 리스트 조회하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "500"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_ADD"
                    },
                    "id": "d33ebb89-04a6-4405-9202-a294476b8b39",
                    "name": "슈퍼로찌 : 11 추가 보너스 조합 임시로 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_LIST"
                    },
                    "id": "69d54d07-d322-4777-b530-ec5890542f7b",
                    "name": "슈퍼로찌 : 12 추가 보너스 조합 임시 등록 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "cms_bonus_target_no",
                            "value": "3"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "100"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_MODIFY"
                    },
                    "id": "9e0782cb-c7ec-47ca-85d1-9bf55406c9d0",
                    "name": "슈퍼로찌 : 13 추가 보너스 조합 대상자 수정하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "type": "Text",
                            "name": "cms_bonus_target_no",
                            "value": "2"
                          },
                          {
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "100"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_BONUS_TARGET_PROCESS"
                    },
                    "id": "1362f9de-9985-477a-8722-9a0163369a22",
                    "name": "슈퍼로찌 : 14 추가 보너스 조합 대상자 최종 반영하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "order_id",
                            "value": "20220619185139-30117450508"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_SHOP_ISSUE_BY_ORDER_ID"
                    },
                    "id": "2c4e23a4-40d3-4dca-be69-91a1dbae9316",
                    "name": "슈퍼로찌 : 21 주문 번호로 지급 조회하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "order_id",
                            "value": "20220619185139-30117450508"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_LOZZI_SHOP_ISSUE_CHECK"
                    },
                    "id": "037e0d5b-17bd-43ba-9092-b85c0e0a802a",
                    "name": "슈퍼로찌 : 22 주문 번호로 지급/미지급인지 확인하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "3"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "order_id",
                            "value": "100"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_LOZZI_SHOP_ISSUE_REG"
                    },
                    "id": "221ceb49-6964-491f-9e9c-c7e6dfdc6152",
                    "name": "슈퍼로찌 : 23 로찌쇼핑 미지급자 지급 하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "lotto_dt",
                            "value": ""
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_ISSUE_COUNT_BY_LOTTO_DT"
                    },
                    "id": "750d72bf-9541-4a44-a248-de0a7e2b6e40",
                    "name": "슈퍼로찌 : 31 현재 회차 총 발행 수 조회하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "ymd",
                            "value": "20221024"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_DAILY_GOLD_AMOUNT_TOTAL"
                    },
                    "id": "42cf5364-1ef7-44c7-8f94-859676696f66",
                    "name": "슈퍼로찌 : 32 일별 황금 상금 지급량 조회",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WITH_READY_LIST"
                    },
                    "id": "3b2eb2e8-af71-4e37-aa9b-541878517e5e",
                    "name": "슈퍼로찌 : 33 소원빌기 검수용 데이터 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_main_no_s",
                            "value": "2"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_main_no_e",
                            "value": ""
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WISH_OPEN_RANGE"
                    },
                    "id": "7a246868-0981-4f1c-bea6-5ef20ce7e4b2",
                    "name": "슈퍼로찌 : 34 소원빌기 검수 통과 시키기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "page",
                            "value": "1"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WISH_LIST"
                    },
                    "id": "622f837f-5ac9-40b6-ab73-3bfa86666bb8",
                    "name": "슈퍼로찌 : 35 소원빌기 데이터 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "my_wish_main_no",
                            "value": "2"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WISH_BLIND"
                    },
                    "id": "71a4dc9f-e5a9-4984-adcb-b84d3dcc8306",
                    "name": "슈퍼로찌 : 36 소원빌기 안보이게 처리하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "wish_comment",
                            "value": "a1\\n2\\n3"
                          }
                        ]
                      },
                      "bodyType": "Text",
                      "textBody": "{\n  \"wish_comment\": \"우\\nto the\\n영\\nto the\\n우\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_WISH_WRITE"
                    },
                    "id": "4f645cd0-a062-46ee-9781-a7fb90f81a9f",
                    "name": "슈퍼로찌 : 37 소원빌기 SalesVook 담당자 글쓰기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/json"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "target_no",
                            "value": "2"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_GOLD_TOP_BANNER_CHANGE"
                    },
                    "id": "aa284997-202d-4a2d-841a-62ed27697f61",
                    "name": "슈퍼로찌 : 40 상단 배너 바꾸기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "30"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_GOLD_ISSUE_TARGET_ADD"
                    },
                    "id": "a43b8b20-5ea7-46af-bfd1-52bb78138c8b",
                    "name": "슈퍼로찌 : 41 추가 황금 번호 조합 임시로 등록하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_GOLD_ISSUE_TARGET_LIST"
                    },
                    "id": "fca3d1b2-7844-40c0-b7b9-5213d19be78b",
                    "name": "슈퍼로찌 : 42 추가 황금 번호 조합 임시 등록 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "cms_bonus_target_no",
                            "value": "1"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "30"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_GOLD_ISSUE_TARGET_MODIFY"
                    },
                    "id": "922b3519-2c93-4e1a-a904-15c2559b7973",
                    "name": "슈퍼로찌 : 43 추가 황금 번호 조합 대상자 수정하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "type": "Text",
                            "name": "cms_bonus_target_no",
                            "value": "2"
                          },
                          {
                            "type": "Text",
                            "name": "issue_cnt",
                            "value": "100"
                          },
                          {
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_GOLD_ISSUE_TARGET_PROCESS"
                    },
                    "id": "9eceb86e-9213-491c-afa0-82c94dae3da1",
                    "name": "슈퍼로찌 : 44 추가 황금 번호 조합 대상자 최종 반영하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "출금신청 결과안내"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "카카오뱅크 미니 카드를 사용하신다면 예금주명을 'ㅇㅇㅇ(미니)'으로 입력하셔서 다시 신청해 주시기 바랍니다."
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_PUSH"
                    },
                    "id": "e9a49fb7-3bff-4ee3-a195-4558e5107f6c",
                    "name": "슈퍼로찌 : 메시지 보내기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "3551310"
                          },
                          {
                            "type": "Text",
                            "name": "product_nm",
                            "value": "5in1 클린마스터"
                          },
                          {
                            "type": "Text",
                            "name": "percentage",
                            "value": "50"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_MAKE_SHOP_LOTTO_REMOVE"
                    },
                    "id": "2b4f7220-80dd-4fbc-88ba-5bcc83322fb1",
                    "name": "슈퍼로찌 : 메이크샵 상품 번호조합 비율 끄기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "3551310"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_nm",
                            "value": "5in1 클린마스터"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "percentage",
                            "value": "50"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_MAKE_SHOP_LOTTO_ADD"
                    },
                    "id": "021d65cb-2d64-41f5-8cf6-be25393cf3ba",
                    "name": "슈퍼로찌 : 메이크샵 상품 번호조합 비율 적용하기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "product_uid",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": [
                          {
                            "enabled": true,
                            "name": "product_uid",
                            "value": "10287602"
                          }
                        ]
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_MAKE_SHOP_LIST_BY_PRODUCT_UID"
                    },
                    "id": "d8d0dd8d-8a17-4de7-897b-c4533b881bff",
                    "name": "슈퍼로찌 : 메이크샵 상품 번호조합 비율 적용한 상품번호로 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.1",
                      "name": "GET"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_MAKE_SHOP_LIST_ALL"
                    },
                    "id": "3eb2b04d-c5cf-4c85-90e9-e6dd39fce0ec",
                    "name": "슈퍼로찌 : 메이크샵 상품 번호조합 비율 적용한 전체 리스트 가져오기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                },
                {
                  "entity": {
                    "type": "Request",
                    "method": {
                      "requestBody": true,
                      "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
                      "name": "POST"
                    },
                    "body": {
                      "formBody": {
                        "overrideContentType": true,
                        "encoding": "application/x-www-form-urlencoded",
                        "items": [
                          {
                            "type": "Text",
                            "name": "mobile_no",
                            "value": "01051304546"
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "title",
                            "value": "SuperLozzi에서 알려드립니다."
                          },
                          {
                            "enabled": true,
                            "type": "Text",
                            "name": "content",
                            "value": "토요일 당첨을 기대해주세요.~"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_TYPE",
                            "value": "transfer"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_REGULER_FLAG",
                            "value": "Y"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_YEAR",
                            "value": "2020"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_MONTH",
                            "value": "03"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_OID",
                            "value": "test202003000001"
                          },
                          {
                            "type": "Text",
                            "name": "PCD_PAY_DATE",
                            "value": "20200325"
                          }
                        ]
                      },
                      "bodyType": "Form",
                      "textBody": "{\n  \"mobile_no\": \"01051304546\",\n  \"title\": \"SuperLozzi에서 알려드립니다.\",\n  \"content\": \"토요일 당첨을 기대해주세요.~\"\n}"
                    },
                    "uri": {
                      "query": {
                        "delimiter": "&",
                        "items": []
                      },
                      "scheme": {
                        "secure": true,
                        "name": "https",
                        "version": "V11"
                      },
                      "host": "cms-kr.superlozzi.com",
                      "path": "/cms/NA_CMS_PUSH_REG"
                    },
                    "id": "4af252d5-d9f8-46cb-9333-4373ce35b911",
                    "name": "슈퍼로찌 : 분할 푸시 보내기",
                    "headers": [
                      {
                        "enabled": true,
                        "name": "User-Agent",
                        "value": "versionCode=19-osType=android-versionName=11"
                      },
                      {
                        "enabled": true,
                        "name": "reg_user_no",
                        "value": "00000000000000000001"
                      },
                      {
                        "enabled": true,
                        "name": "Content-Type",
                        "value": "application/x-www-form-urlencoded"
                      },
                      {
                        "enabled": true,
                        "name": "Accept",
                        "value": "application/json"
                      }
                    ]
                  }
                }
              ]
            }
          ]
        }
      ]
    }
""".trimIndent()
