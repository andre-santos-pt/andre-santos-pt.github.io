#!/bin/sh

dblpurl='https://dblp.org/search/publ/api?q=author%3AAndré_L._Santos_0001%3A&format=json'
curl $dblpurl | jq '.result.hits.hit' > pubsfetch.json
jq 'del(.[] | select(.info.venue == "CoRR"))' pubsfetch.json > _data/pubs.json
rm pubsfetch.json
