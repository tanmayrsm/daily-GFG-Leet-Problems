# Elasticsearch – quick notes for hotel search

## 1. Why Elasticsearch here?

- Built specifically for search:
  - Inverted index (term → list of documents).
  - Scoring (BM25), fuzziness, analyzers, multiple languages.[web:49][web:55]
- Great for:
  - "Mar che" → "Marriott Chennai".
  - Ranking by relevance, popularity, rating.

Postgres stays system of record; Elasticsearch is for search UX.

---

## 2. Inverted index (very short)

Instead of:

- Document → [words...]

it keeps:

- word → [document IDs...]

Example:

- marriott → [Doc1, Doc3]
- chennai → [Doc1, Doc2]

Query "Marriott Chennai":

- Get marriott list and chennai list, intersect, then score.[web:59][web:60]

Nice visuals/explanations:

- https://www.geeksforgeeks.org/dbms/inverted-index/[web:59]
- https://en.wikipedia.org/wiki/Inverted_index[web:60]

---

## 3. ES cluster basics

- Index: e.g., `hotels`.
- Document: one hotel JSON.
- Shards: index split into parts; each shard has replicas for HA.
- Query hits all shards, each shard searches local inverted index, then results are merged.[web:49][web:55]

Official diagrams (worth looking at):

- https://www.elastic.co/guide/en/elasticsearch/reference/current/scalability.html[web:49]

---

## 4. Hotel index + query example

Mapping (simplified):

```json
PUT /hotels
{
  "mappings": {
    "properties": {
      "hotel_id": { "type": "keyword" },
      "name":     { "type": "text" },
      "city":     { "type": "text" },
      "rating":   { "type": "float" }
    }
  }
}
```

Autocomplete-style query (top 7):

```json
GET /hotels/_search
{
  "size": 7,
  "query": {
    "multi_match": {
      "query": "mar",
      "fields": ["name^3", "city"],
      "type": "best_fields",
      "fuzziness": "AUTO"
    }
  }
}
```

- `name^3`: boost hotel name over city.
- `fuzziness: "AUTO"`: handle small typos like "mariot".[web:49][web:52]

---

## 5. When to choose ES vs Postgres search

- Start with Postgres + GIN/trigrams:
  - Simpler ops, strong consistency.

- Move to ES when:
  - Many hotels, many languages.
  - Need heavy relevance tuning, typo tolerance, synonyms.[web:55][web:58]
