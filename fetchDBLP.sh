#!/bin/sh

dblpurl='https://dblp.org/search/publ/api?q=author%3AAndré_L._Santos_0001%3A&format=json&h=100'
curl $dblpurl | jq '.result.hits.hit' > pubsfetch.json
jq -s 'add | sort_by(.info.year | tonumber) | reverse' pubsfetch.json _data/extrapubs.json  > merge.json
jq 'del(.[] | select(.info.venue == "CoRR"))' merge.json > _data/pubs.json
rm pubsfetch.json
rm merge.json
