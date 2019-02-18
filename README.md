# Curiosity Rover API

## What is this API?
This API is a Scala implementation of the classic Rover challenge.
It provides methods for landing, moving, turning (left and right) and listing
a rover that's been deployed in a cartesian exploring plane in a distant planet

## Prerequisites

The following software need to be installed in order for this API to be built

1. Java8
2. [sbt](https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Mac.html)

## Building the API

To build this API, execute the run.sh script. 

## API methods

The API provides the following methods:

#### `POST /rovers`

Creates a new rover

##### Request 

```
{
    "position": {
	"x": 1,
	"y": 4
    },    
    "facingDirection": "East",  
    "explorationSurfaceEdge": {
	"x": 5,
	"y": 5
    }
}
```

The `facingDirection` property supports `North`, `South`, `East` and `West` values.
Any other value will result in a HTTP 400 response

##### Response

```
{
    "id": "558fe219",
    "name": "hungry_brattain",
    "landingTime": 1550441475,
    "roverActions": {
        "move": "/rover/558fe219/move",
        "turn-left": "/rover/558fe219/turn-left",
        "turn-right": "/rover/558fe219/turn-right"
    }
}
```

The `roverActions` property contains the links that might be used to move 
and turn the robot. They are al POST requests.

Also, the `Location` header contains the endpoint for getting this resource data

#### `GET /rovers` 

##### Request 

No request body

##### Response

Returns the current state of all deployed rovers

#### `GET /rover/<id>`

##### Request 

No request body

##### Response
 
Returns the current state of a specific rover

#### `POST /rover/<id>/move`

Moves the identified rover.

##### Request

No request body

##### Response

HTTP codes

> 200 (The rover has successfully moved. Return current state)

> 422 (Moving the rover would cause it to go beyond the determined edge) 

####  `POST /rover/<id>/turn-left` 

Turns the rover left

##### Request

No request body. HTTP 200 if OK

##### Response

Rover's current state

#### `POST /rover/<id>/turn-right`

Turns the rover right

##### Request

No request body

##### Response

Rover's current state 

TODO

1. Create docker image for application