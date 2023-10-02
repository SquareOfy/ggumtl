export const changeDate = (date:string|undefined) :string => {
  if (date === undefined) {
    return "0"
  }
  const createdDate = new Date(date)
  const year = createdDate.getFullYear()
  let month :string = String(createdDate.getMonth() +1)
  let day :string = String(createdDate.getDate()
)
  if (Number(month) < 10) {
    month = `0${month}`
  }
  if (Number(day) < 10) {
    day = `0${day}`
  }

  return [year, month, day].join("/")
}

export const changeDateHour = (date: string | undefined) :string => {
  if (date === undefined) return "0"
    const createdDate = new Date(date)
    const year = createdDate.getFullYear()
    let month :string = String(createdDate.getMonth() +1)
    let day :string = String(createdDate.getDate())
    let hour:string = String(createdDate.getHours())
    let miniutes:string = String(createdDate.getMinutes())
    if (Number(month) < 10) {
      month = `0${month}`
    }
    if (Number(day) < 10) {
      day = `0${day}`
    }
    if (Number(hour) < 10) {
      hour = `0${hour}`
    }
    if (Number(miniutes) < 10) {
      miniutes = `0${miniutes}`
    }
  
    return [year, month, day].join(".") + " " + [hour, miniutes].join(":")
}
