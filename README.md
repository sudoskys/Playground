# Code

零散代码/非项目代码


[CodeServer-GPG签名问题](https://stackoverflow.com/questions/52808365/git-error-gpg-failed-to-sign-the-data-on-linux)

```shell
export GPG_TTY=$(tty)
```


## Reading all records
Get all records inside a krate.
```bash
curl -X GET 'https://krat.es/demokrate12345abcdef'
```
+++ Response
```json
[
  {
    "_id": "618643b",
    "createdAt": "202039Z",
    "updatedAt": "2021-39Z",
    "name": "Tony Stark",
    "age": 35,
    "aka": "Iron Man"
  },
  ...
]
```
## Reading records from a collection

Get all records inside a collection named `avengers`

```bash
curl -X GET 'https://krat.es/demokrate12345abcdef/avengers'
```
## Reading a specific record
Get a specific record with ID : `61865154a0b2f9c31e61243b`

```bash
curl -X GET 'https://krat.es/demokrate12345abcdef/record/61865154a0b2f9c31e61243b'
```
## Filter records
You can filter records inside a krate with the given parameters

| skip  | Used to skip certain no. of records. Can be used for pagination.          | 0       |
| limit | Used to limit the results to a specific count. Can be used for pagination | 25      |
| query | Query for filtering values. Check out the format below.                   |         |

Filter params are also applicable on collections. Just include the collection ID as well in the request URL along with the other filters and query.

### Query
Query is a special param that can be used to add custom filters to the request.
You can pass a filter in a query by passing them in URL param `query` as shown below:

+++ Request

```sh
curl -X GET 'https://krat.es/demokrate12345abcdef?query=name:Tony%20Stark,age:>30'
```
The above sample will look for the name `Tony Stark` and age greater than 30 in the given krate. You can filter on `Number`, `String` & `Boolean` values only.
**Different filters for Numeric values.**

| To filter values greater than or less than a specific value          | `query=age:>10` or `query=age:<10`   |
| To filter values greater (or less) than or equal to a specific value | `query=age:>=10` or `query=age:<=10` |
| To filter values that match a specific value.                        | `query=age:=10`                      |

**Different filters for String values.**

| Filter values that start with a specific string                    | `query=name:Tony*`      |
| Filter values that end with a specific string                      | `query=name:*Stark`     |
| Filter values where a specific string appears anywhere in a string | `query=name:*ny*`       |
| Filter values that match a specific string                         | `query=name:Tony Stark` |

```sh
https://krat.es/demokrate12345abcdef?query=name:Tony%20Stark,age:>30,aka:Iron*&limit=1
```
```python
import requests
import json

class KratesAPI:
    def __init__(self, api_key):
        self.base_url = 'https://krat.es'
        self.api_key = api_key
        
    def create_record(self, krate_id, data, collection_id=None):
        url = f'{self.base_url}/{krate_id}'
        if collection_id:
            url += f'/{collection_id}'
        headers = {
            'content-type': 'application/json',
            'x-api-key': self.api_key
        }
        response = requests.post(url, headers=headers, data=json.dumps(data))
        return response.json()
    
    def list_records(self, krate_id, collection_id=None, skip=0, limit=25, query=None):
        url = f'{self.base_url}/{krate_id}'
        if collection_id:
            url += f'/{collection_id}'
        params = {'skip': skip, 'limit': limit}
        if query:
            params['query'] = query
        headers = {'x-api-key': self.api_key}
        response = requests.get(url, headers=headers, params=params)
        return response.json()
    
    def get_record(self, krate_id, record_id):
        url = f'{self.base_url}/{krate_id}/record/{record_id}'
        headers = {'x-api-key': self.api_key}
        response = requests.get(url, headers=headers)
        return response.json()
```