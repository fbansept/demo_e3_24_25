```toml
name = 'create reservation journee'
method = 'POST'
url = 'http://localhost:8080/reservation-place-journee'
sortWeight = 3000000
id = 'f3bf901a-141b-4533-bd3c-387f253a86b9'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjQGMuY29tIn0.QLHwIuq4zP0XTBqaW4JeBO0WZ_BhaKfI8n8w1-OBhdo'

[body]
type = 'JSON'
raw = '''
{
  "place" : {
    "id" : 2
  }
}'''
```
