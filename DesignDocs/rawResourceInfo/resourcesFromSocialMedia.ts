

type foodResourceType = {
    name: string,
    type: string,
    contactName: string,
    contactEmail: string, //must be nullable for privacy!
    location:string,
    dateAvailable: string;
};


let foodpantry1: foodResourceType = {
    name: "Free Little Pantry on Milwaukee and Lansing",
    type: "Free Little Pantry",
    contactName: "Allison Werner",
    contactEmail:"",
    location: "3489 Milwaukee Street., Madison, Wi 53714",
    dateAvailable: "ongoing",
};