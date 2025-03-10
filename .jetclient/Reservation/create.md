```toml
name = 'create'
method = 'POST'
url = 'http://localhost:8080/reservation'
sortWeight = 2000000
id = '4658b81d-3a90-4668-99b0-803e56fbbce7'

[body]
type = 'JSON'
raw = '''
{
  "dateDebut" : "2025-02-01T12:25:01",
  "dateFin" : "2025-02-01T12:25:01",
  "place" : {
    "id" : 2
  }
}'''
```
